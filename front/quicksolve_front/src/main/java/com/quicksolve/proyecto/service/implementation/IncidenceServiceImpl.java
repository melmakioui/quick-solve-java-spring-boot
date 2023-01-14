package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceState;
import com.quicksolve.proyecto.mapper.IncidenceMapper;
import com.quicksolve.proyecto.repository.IncidenceRepository;
import com.quicksolve.proyecto.repository.IncidenceStateRepository;
import com.quicksolve.proyecto.service.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenceServiceImpl implements IncidenceService {

    private final IncidenceRepository incidenceRepository;
    private final IncidenceStateRepository incidenceStateRepository;

    @Autowired
    public IncidenceServiceImpl(IncidenceRepository incidenceRepository
            , IncidenceStateRepository incidenceStateRepository) {
        this.incidenceRepository = incidenceRepository;
        this.incidenceStateRepository = incidenceStateRepository;
    }

    @Override
    public List<IncidenceDTO> list() {
        return incidenceRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Incidence findById(Long id) {
        return incidenceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incidence does not exist" + id));
    }

    @Override
    public void save(Incidence incidence) {

        if (incidence.getDepartment().getId() == 0) incidence.setDepartment(null);

        incidence.setDateStart(LocalDateTime.now());
        IncidenceState incidenceState = incidenceStateRepository.getReferenceById(1L); //Siempre tendra Waiting por default al insertar una incidencia
        incidence.setIncidenceState(incidenceState);
        incidenceRepository.save(incidence);
    }

    @Override
    public void delete(Incidence incidence) {
        incidenceRepository.findById(incidence.getId())
                .orElseThrow(() -> new IllegalArgumentException("Incidence does not exist" + incidence.getId()));
        incidenceRepository.delete(incidence);
    }

    @Override
    public void update(Incidence incidence) {

        System.out.println(incidence.getId());
        incidenceRepository.findById(incidence.getId())
                .orElseThrow(() -> new IllegalArgumentException("Incidence does not exist" + incidence.getId()));

        incidenceRepository.save(incidence);
    }

    private IncidenceDTO convertToDTO(Incidence incidence) {

        return IncidenceMapper.INSTANCE.incidenceDTO(incidence, //Mostrar tambien si el departamento es null
                incidence.getDepartment() != null ? incidence.getDepartment()
                        .getDepartmentLanguage().stream().findFirst().get() : null,
                incidence.getIncidenceState().
                        getIncidenceStateLanguage()
                        .stream().findFirst().get());
    }
}
