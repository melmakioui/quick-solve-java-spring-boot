package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.IncidenceMessageDTO;
import com.quicksolve.proyecto.entity.*;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.mapper.IncidenceMessageMapper;
import com.quicksolve.proyecto.repository.IncidenceMessageRepository;
import com.quicksolve.proyecto.repository.IncidenceRepository;
import com.quicksolve.proyecto.repository.UserIncidenceRepository;
import com.quicksolve.proyecto.service.IncidenceMessageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenceMessageServiceImpl implements IncidenceMessageService {


    @Autowired
    private IncidenceMessageRepository incidenceMessageRepository;
    @Autowired
    private UserIncidenceRepository userIncidenceRepository;

    @Override
    public IncidenceMessageDTO findById(long id) {
        IncidenceMessage message = incidenceMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el mensaje"));
        return convertToDTO(message);
    }

    @Override
    public List<IncidenceMessageDTO> findAllByIncidenceIdAndUserId(long incidenceId, long userId) {

        return incidenceMessageRepository.findAllByIncidenceIdAndUserIdOrderByOrderrDesc(incidenceId, userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<IncidenceMessageDTO> findAllByIncidenceId(long incidenceId) {

        return incidenceMessageRepository.findAllByIncidenceIdOrderByOrderrDesc(incidenceId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public void save(IncidenceMessageDTO incidenceMessageDTO, long incidenceId, FullUserDTO fullUserDTO) {

        IncidenceMessage incidenceMessage = IncidenceMessageMapper.INSTANCE.toIncidenceMessage(incidenceMessageDTO);

        incidenceMessage.setOrderr(incidenceMessageRepository.countByIncidenceId(incidenceId) + 1);
        incidenceMessage.setDateHour(LocalDateTime.now());

        if (fullUserDTO.getType() == UserType.USER) {
            incidenceMessage.setIncidence(userIncidenceRepository.findByIncidenceIdAndUserId(incidenceId, fullUserDTO.getId()));
            incidenceMessage.setUser(userIncidenceRepository.findByIncidenceIdAndUserId(incidenceId, fullUserDTO.getId()));
        } else {
            //TODO: como es Tecnico hay que enviar por correo electronico al usuario que creo la incidencia
            incidenceMessage.setIncidence(userIncidenceRepository.findByIncidenceIdAndTechId(incidenceId, fullUserDTO.getId()));
            incidenceMessage.setTech(userIncidenceRepository.findByIncidenceIdAndTechId(incidenceId, fullUserDTO.getId()));
        }

        incidenceMessageRepository.save(incidenceMessage);
    }

    @Override
    @Transactional
    public void delete(long incidenceMessageId, long incidenceId, FullUserDTO fullUserDTO) {

        if (fullUserDTO.getType() == UserType.USER) {
            System.out.println("USER");
            incidenceMessageRepository.deleteByIdAndIncidenceIdAndUserId(incidenceMessageId, incidenceId, fullUserDTO.getId());
        }

        if (fullUserDTO.getType() == UserType.TECH) {
            System.out.println("TECH");
            incidenceMessageRepository.deleteByIdAndIncidenceIdAndTechId(incidenceMessageId, incidenceId, fullUserDTO.getId());
        }
    }

    @Override
    public void update(IncidenceMessageDTO incidenceMessageDTO, long incidenceId, long incidenceMessageId, FullUserDTO fullUserDTO) {

        IncidenceMessage incidenceMessage = IncidenceMessageMapper.INSTANCE.toIncidenceMessage(incidenceMessageDTO);
        IncidenceMessage incidenceMessageUpdate = incidenceMessageRepository.findById(incidenceMessageId)
                .orElseThrow(() -> new RuntimeException("No se encontro el mensaje"));

        if (fullUserDTO.getType() == UserType.USER) {
            incidenceMessageUpdate.setBody(incidenceMessage.getBody());
            incidenceMessageUpdate.setAction(incidenceMessage.isAction());
            incidenceMessageRepository.save(incidenceMessageUpdate);
        }

        if (fullUserDTO.getType() == UserType.TECH) {
            incidenceMessageUpdate.setBody(incidenceMessage.getBody());
            incidenceMessageUpdate.setAction(incidenceMessage.isAction());
            incidenceMessageRepository.save(incidenceMessageUpdate);
        }

    }

    @Override
    public void verifyOwner(long incidenceMessageId, FullUserDTO fullUserDTO) {

        if (fullUserDTO.getType() == UserType.USER) {
            IncidenceMessage im = incidenceMessageRepository.findByIdAndUserId(incidenceMessageId, fullUserDTO.getId());
            if (im == null) {
                throw new RuntimeException("No tiene acceso a modificar este mensaje");
            }
        }

        if (fullUserDTO.getType() == UserType.TECH) {
            IncidenceMessage im = incidenceMessageRepository.findMessageByTech(incidenceMessageId, fullUserDTO.getId());
            if (im == null) {
                throw new RuntimeException("No tienes acceso a modificar este mensaje");
            }
        }

    }

    private IncidenceMessageDTO convertToDTO(IncidenceMessage incidenceMessage) {
        if (incidenceMessage.getUser() != null) {
            return IncidenceMessageMapper.INSTANCE.toIncidenceMessageDTO(incidenceMessage);
        }

        if (incidenceMessage.getTech() != null) {
            return IncidenceMessageMapper.INSTANCE.toIncidenceMessageDTO(incidenceMessage);
        }

        return null;
    }
}
