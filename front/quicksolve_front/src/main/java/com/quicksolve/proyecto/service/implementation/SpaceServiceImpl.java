package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.SpaceDTO;
import com.quicksolve.proyecto.entity.Space;
import com.quicksolve.proyecto.mapper.SpaceMapper;
import com.quicksolve.proyecto.repository.SpaceRepository;
import com.quicksolve.proyecto.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceServiceImpl  implements SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Override
    public List<SpaceDTO> getAllSpaces() {
        return spaceRepository.findAll()
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

    private SpaceDTO convertDataIntoDTO(Space space) {
       return SpaceMapper.INSTANCE.departmentDTO(space);
    }
}
