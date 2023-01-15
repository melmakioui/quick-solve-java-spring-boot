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
    public IncidenceDTO findByIdDTO(long id) {
        return convertToDTO(incidenceRepository.findById(id).get());
    }

    @Override
    public Incidence findById(long id) {
        return incidenceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incidence does not exist" + id));
    }

    @Override
    public void save(Incidence incidence) {

        if (incidence.getDepartment().getId() == 0) incidence.setDepartment(null);

        incidence.setDateStart(LocalDateTime.now());
        IncidenceState incidenceState = incidenceStateRepository.getReferenceById(1L); //Siempre tendra Waiting/En espera por default al insertar una incidencia
        incidence.setIncidenceState(incidenceState);
        incidenceRepository.save(incidence);
    }

    @Override
    public void delete(long id) {

        long idDelete = incidenceRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("Incidence does not exist" + id)).getIncidenceState().getId();

        if (idDelete > 1L)  //Para que no eliminen alguna en estado solucionando, solucionado o cancelado
            throw new IllegalArgumentException("Status of the incidence does not allow deletion");

        if (incidenceRepository.existsById(id)) {
            incidenceRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Incidence does not exist");
        }
    }

    @Override
    public void update(Incidence incidence, long id) {

        long idDelete = incidenceRepository.findById(incidence.getId())
                .orElseThrow( () -> new IllegalArgumentException("Incidence does not exist")).getIncidenceState().getId();

        if (idDelete > 1L)  //Para que no modifiquen alguna en estado solucionando, solucionado o cancelado
            throw new IllegalArgumentException("Incidence does not exist");

        incidenceRepository.findById(incidence.getId())
                .orElseThrow(() -> new IllegalArgumentException("Incidence does not exist" + incidence.getId()));

        Incidence updateIncidence = incidenceRepository.findById(id).get();

        updateIncidence.setTitle(incidence.getTitle());
        updateIncidence.setDescription(incidence.getDescription());
        updateIncidence.setDepartment(incidence.getDepartment());

        incidenceRepository.save(updateIncidence);
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
