package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.IncidenceMessageDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceMessage;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserIncidence;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.mapper.IncidenceMessageMapper;
import com.quicksolve.proyecto.mapper.UserMapper;
import com.quicksolve.proyecto.repository.IncidenceMessageRepository;
import com.quicksolve.proyecto.repository.IncidenceRepository;
import com.quicksolve.proyecto.repository.UserIncidenceRepository;
import com.quicksolve.proyecto.repository.UserRepository;
import com.quicksolve.proyecto.service.IncidenceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidenceMessageServiceImpl implements IncidenceMessageService {

    @Autowired
    private IncidenceMessageRepository incidenceMessageRepository;

    @Autowired
    private IncidenceRepository incidenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserIncidenceRepository userIncidenceRepository;

    @Override
    public List<IncidenceMessageDTO> findAllByIncidenceId(long incidenceId) {
        return incidenceMessageRepository.findAllByIncidenceId(incidenceId)
                .stream()
                .map(this::toIncidenceMessageDTO)
                .toList();
    }

    @Override
    public IncidenceMessageDTO findByIdAndIncidenceIdAndUserId(long messageId, long incidenceId, long userId) {
        IncidenceMessage incidenceMessage = incidenceMessageRepository.findByIdAndIncidenceIdAndUserId(messageId, incidenceId, userId);
        if (incidenceMessage == null) {
            throw new RuntimeException("No se encontro el mensaje");
        }
        return toIncidenceMessageDTO(incidenceMessage);
    }

    @Override
    public void save(IncidenceMessageDTO incidenceMessageDTO, long incidenceId, long userId) {
        IncidenceMessage message = IncidenceMessageMapper.INSTANCE.toIncidenceMessage(incidenceMessageDTO);

        Incidence incidence = incidenceRepository.findById(incidenceId)
                .orElseThrow(() -> new RuntimeException("No se encontro la incidencia"));

        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));

        //TODO mirar si usuario es el dueño de la incidencia y el tecnico tiene acceso a la incidencia

        int messages = incidenceMessageRepository.countAllByIncidenceId(incidenceId) + 1;
        message.setDateHour(LocalDateTime.now());
        message.setOrderr(messages);
        message.setIncidence(incidence);
        message.setUser(owner);
        incidenceMessageRepository.save(message);
    }


    @Override
    public void update(IncidenceMessageDTO incidenceMessageDTO, long incidenceId, long messageId, long userId) {

        IncidenceMessage incidenceMessageUpdate = incidenceMessageRepository
                .findByIdAndIncidenceIdAndUserId(messageId, incidenceId, userId);

        if (incidenceMessageUpdate == null) {
            throw new RuntimeException("No se encontro el mensaje");
        }

        incidenceMessageUpdate.setBody(incidenceMessageDTO.getBody());
        incidenceMessageUpdate.setAction(incidenceMessageDTO.isAction());
        incidenceMessageUpdate.setModified(true);
        incidenceMessageRepository.save(incidenceMessageUpdate);
    }

    @Override
    public void delete(long messageId, long incidenceId, long userId) {

        IncidenceMessage incidenceMessage = incidenceMessageRepository
                .findByIdAndIncidenceIdAndUserId(messageId, incidenceId, userId);

        if (incidenceMessage == null) {
            throw new RuntimeException("No se encontro el mensaje");
        }

        incidenceMessageRepository.deleteById(messageId);
    }

    @Override
    public IncidenceMessageDTO findById(long id) {
        IncidenceMessage message = incidenceMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el mensaje"));
        return toIncidenceMessageDTO(message);
    }


    private IncidenceMessageDTO toIncidenceMessageDTO(IncidenceMessage incidenceMessage) {
        FullUserDTO user = UserMapper.INSTANCE.userToDTO(userRepository.getReferenceById(incidenceMessage.getUser().getId()));
        IncidenceMessageDTO incidenceMessageDTO = IncidenceMessageMapper.INSTANCE.toIncidenceMessageDTO(incidenceMessage);
        incidenceMessageDTO.setUser(user);
        return incidenceMessageDTO;
    }
}
