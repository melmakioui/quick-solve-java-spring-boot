package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceState;
import com.quicksolve.proyecto.mapper.IncidenceMapper;
import com.quicksolve.proyecto.repository.*;
import com.quicksolve.proyecto.service.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenceServiceImpl implements IncidenceService {

    private final Long INCIDENCE_WAITING_STATE = 1L;
    private final IncidenceRepository incidenceRepository;
    private final IncidenceStateRepository incidenceStateRepository;
    private final DepartmentRepository departmentRepository;
    private final SpaceRepository spaceRepository;

    @Autowired
    public IncidenceServiceImpl(IncidenceRepository incidenceRepository, IncidenceStateRepository incidenceStateRepository,
                                DepartmentRepository departmentRepository,
                                SpaceRepository spaceRepository) {
        this.incidenceRepository = incidenceRepository;
        this.incidenceStateRepository = incidenceStateRepository;
        this.departmentRepository = departmentRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public List<FullIncidenceDTO> list() {
        return incidenceRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FullIncidenceDTO findById(long id) {
        Incidence incidence =  incidenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la incidencia"));
        return convertToDTO(incidence);
    }

    @Override
    public void save(FullIncidenceDTO fullIncidenceDTO) {

        long departmentId = fullIncidenceDTO.getDepartment().getId();
        long spaceId = fullIncidenceDTO.getSpace().getId();

        if (departmentId == -1) {
            fullIncidenceDTO.setDepartment(null);
        }

        if (spaceId == -1) {
            fullIncidenceDTO.setSpace(null);
        }

        Incidence incidence = IncidenceMapper.INSTANCE.dtoToIncidence(fullIncidenceDTO);
        incidence.setDateStart(LocalDateTime.now());
        IncidenceState waitingState = incidenceStateRepository.getReferenceById(INCIDENCE_WAITING_STATE);
        incidence.setIncidenceState(waitingState);
        incidenceRepository.save(incidence);
    }

    @Override
    public void delete(long id) {
        Incidence incidence = incidenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la incidencia"));

        if (incidence.getIncidenceState().getId() != INCIDENCE_WAITING_STATE) {
            throw new RuntimeException("No se puede eliminar una incidencia que no esta en estado de espera");
        }

        incidenceRepository.delete(incidence);
    }

    @Override
    public void update(FullIncidenceDTO fullIncidenceDTO) {

        long departmentId = fullIncidenceDTO.getDepartment().getId();
        long spaceId = fullIncidenceDTO.getSpace().getId();

        if (departmentId == -1) {
            fullIncidenceDTO.setDepartment(null);
        }

        if (spaceId == -1) {
            fullIncidenceDTO.setSpace(null);
        }

        Incidence incidence = incidenceRepository.findById(fullIncidenceDTO.getId())
                .orElseThrow(() -> new RuntimeException("No se encontro la incidencia"));

        if (incidence.getIncidenceState().getId() != INCIDENCE_WAITING_STATE) {
            throw new RuntimeException("No se puede modificar una incidencia que no esta en estado de espera");
        }

        Incidence incidenceToUpdate = IncidenceMapper.INSTANCE.dtoToIncidence(fullIncidenceDTO);
        incidenceToUpdate.setIncidenceState(incidence.getIncidenceState());
        incidenceRepository.save(incidenceToUpdate);
    }

    private FullIncidenceDTO convertToDTO(Incidence incidence) {
        return IncidenceMapper.INSTANCE.incidenceToDTO(incidence);
    }
}
