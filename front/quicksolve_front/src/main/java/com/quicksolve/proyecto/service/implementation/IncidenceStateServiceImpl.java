package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.IncidenceStateDTO;
import com.quicksolve.proyecto.entity.IncidenceState;
import com.quicksolve.proyecto.entity.IncidenceStateLanguage;
import com.quicksolve.proyecto.mapper.IncidenceStateMapper;
import com.quicksolve.proyecto.repository.IncidenceStateLanguageRepository;
import com.quicksolve.proyecto.repository.IncidenceStateRepository;
import com.quicksolve.proyecto.service.IncidenceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenceStateServiceImpl implements IncidenceStateService {

    @Autowired
    private IncidenceStateRepository incidenceStateRepository;
    @Autowired
    private IncidenceStateLanguageRepository incidenceStateLanguageRepository;

    @Override
    public List<IncidenceStateDTO> list() {
        return incidenceStateRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IncidenceStateDTO findById(Long id) {
        IncidenceState incidenceState = incidenceStateRepository.findById(id)
                .orElse(null);

        if (incidenceState == null) {
            return null;
        }

        return convertToDTO(incidenceState);
    }

    private IncidenceStateDTO convertToDTO(IncidenceState incidenceState) {
        IncidenceStateLanguage incidenceStateLanguage = incidenceStateLanguageRepository.findByStatusIdAndLanguageId(incidenceState.getId(), 1L);

        return IncidenceStateMapper.INSTANCE.toIncidenceStateDTO(incidenceStateLanguage);
    }
}
