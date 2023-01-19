package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.SpaceDTO;
import com.quicksolve.proyecto.entity.Space;
import com.quicksolve.proyecto.entity.SpaceLanguage;
import com.quicksolve.proyecto.mapper.SpaceMapper;
import com.quicksolve.proyecto.repository.SpaceLanguageRepository;
import com.quicksolve.proyecto.repository.SpaceRepository;
import com.quicksolve.proyecto.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SpaceServiceImpl  implements SpaceService {

    private final Long ESPANOL_LANGUAGE_ID = 1L;

    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private SpaceLanguageRepository spaceLanguageRepository;

    @Override
    public List<SpaceDTO> list() {
        List<Space> spaces = spaceRepository.findAll();


        if (spaces.isEmpty()) {
            return Collections.emptyList();
        }

        return spaces
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SpaceDTO findById(Long id) {
        Space space = spaceRepository.findById(id)
                .orElse(null);

        if (space == null) {
            return null;
        }

        return convertDataIntoDTO(space);
    }

    private SpaceDTO convertDataIntoDTO(Space space) {
       SpaceLanguage spaceLanguage = spaceLanguageRepository.findBySpaceIdAndLanguageId(
               space.getId(),
               ESPANOL_LANGUAGE_ID);
       return SpaceMapper.INSTANCE.spaceDTO(spaceLanguage);
    }
}
