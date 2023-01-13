package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.mapper.IncidenceMapper;
import com.quicksolve.proyecto.repository.IncidenceRepository;
import com.quicksolve.proyecto.service.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenceServiceImpl implements IncidenceService {

    @Autowired
    private IncidenceRepository incidenceRepository;

    @Override
    public List<IncidenceDTO> list() {
        return incidenceRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IncidenceDTO get(Long id) {
        return convertToDTO(incidenceRepository.findById(id).get());
    }

    @Override
    public boolean save(IncidenceDTO incidenceDTO) {
        return false;
    }

    @Override
    public boolean delete(IncidenceDTO incidenceDTO) {
        return false;
    }

    @Override
    public boolean update(IncidenceDTO incidenceDTO) {
        return false;
    }

    private IncidenceDTO convertToDTO(Incidence incidence) {

        return IncidenceMapper.INSTANCE.incidenceDTO(incidence,
                incidence.getDepartment().getDepartmentLanguage()
                        .stream()
                        .findFirst()
                        .get(),
                incidence.getIncidenceState().
                        getIncidenceStateLanguage()
                        .stream().findFirst().get());

    }


}
