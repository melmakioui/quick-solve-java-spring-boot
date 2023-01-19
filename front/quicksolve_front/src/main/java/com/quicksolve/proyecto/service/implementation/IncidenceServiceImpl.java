package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.IncidenceDTO;
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

    @Autowired
    public IncidenceServiceImpl(IncidenceRepository incidenceRepository, IncidenceStateRepository incidenceStateRepository) {
        this.incidenceRepository = incidenceRepository;
        this.incidenceStateRepository = incidenceStateRepository;
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
        Incidence incidence = IncidenceMapper.INSTANCE.DTOtoIncidence(fullIncidenceDTO);
        incidence.setDateStart(LocalDateTime.now());
        IncidenceState waitingState = incidenceStateRepository.getReferenceById(INCIDENCE_WAITING_STATE);
        incidence.setIncidenceState(waitingState);
        incidenceRepository.save(incidence);
    }


    @Override
    public void delete(long id) {
        //
    }

    @Override
    public void update(FullIncidenceDTO fullIncidenceDTO, long id) {
        //
    }

    private FullIncidenceDTO convertToDTO(Incidence incidence) {
        return IncidenceMapper.INSTANCE.incidenceDTO(incidence);
    }
}
