package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceState;
import com.quicksolve.proyecto.entity.UserIncidence;
import com.quicksolve.proyecto.mapper.IncidenceMapper;
import com.quicksolve.proyecto.mapper.UserMapper;
import com.quicksolve.proyecto.repository.*;
import com.quicksolve.proyecto.service.IncidenceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class IncidenceServiceImpl implements IncidenceService {

    private final Long INCIDENCE_WAITING_STATE = 1L;

    @Autowired
    private  IncidenceRepository incidenceRepository;

    @Autowired
    private  UserIncidenceRepository userIncidenceRepo;

    @Autowired
    private  IncidenceStateRepository incidenceStateRepository;

    @Override
    public List<FullIncidenceDTO> list(FullUserDTO userDTO) {
        List<Incidence> incidences = userIncidenceRepo.findAllByUser(UserMapper.INSTANCE.DTOtoUser(userDTO))
                .stream()
                .map(UserIncidence::getIncidence)
                .toList();
        return incidences.stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FullIncidenceDTO findById(long id) {
        Incidence incidence = incidenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la incidencia"));
        return convertToDTO(incidence);
    }

    @Override
    public void save(FullIncidenceDTO fullIncidenceDTO) {

        checkDepartmentAndSpace(fullIncidenceDTO);

        Incidence incidence = IncidenceMapper.INSTANCE.dtoToIncidence(fullIncidenceDTO);
        incidence.setDateStart(LocalDateTime.now());
        IncidenceState waitingState = incidenceStateRepository.getReferenceById(INCIDENCE_WAITING_STATE);
        incidence.setIncidenceState(waitingState);
        incidenceRepository.save(incidence);
    }

    @Override
    public void save(FullIncidenceDTO fullIncidenceDTO, FullUserDTO userDTO) {

        checkDepartmentAndSpace(fullIncidenceDTO);

        Incidence incidence = IncidenceMapper.INSTANCE.dtoToIncidence(fullIncidenceDTO);
        incidence.setDateStart(LocalDateTime.now());
        IncidenceState waitingState = incidenceStateRepository.getReferenceById(INCIDENCE_WAITING_STATE);
        incidence.setIncidenceState(waitingState);
        Incidence newIncidence = incidenceRepository.save(incidence);

        UserIncidence userIncidence = new UserIncidence();
        userIncidence.setIncidence(newIncidence);
        userIncidence.setUser(UserMapper.INSTANCE.DTOtoUser(userDTO));
        userIncidenceRepo.save(userIncidence);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Incidence incidence = incidenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la incidencia"));

        if (incidence.getIncidenceState().getId() != INCIDENCE_WAITING_STATE) {
            throw new RuntimeException("No se puede eliminar una incidencia que no esta en estado de espera");
        }

        //UserIncidence tiene una relacion de tipo cascade.ALL, por lo que al eliminar la incidencia
        userIncidenceRepo.deleteByIncidenceId(incidence.getId());
    }

    @Override
    public void update(FullIncidenceDTO fullIncidenceDTO) {

        checkDepartmentAndSpace(fullIncidenceDTO);

        Incidence incidence = incidenceRepository.findById(fullIncidenceDTO.getId())
                .orElseThrow(() -> new RuntimeException("No se encontro la incidencia"));

        if (incidence.getIncidenceState().getId() != INCIDENCE_WAITING_STATE) {
            throw new RuntimeException("No se puede modificar una incidencia que no esta en estado de espera");
        }

        Incidence incidenceToUpdate = IncidenceMapper.INSTANCE.dtoToIncidence(fullIncidenceDTO);
        incidenceToUpdate.setDateStart(incidence.getDateStart());
        incidenceToUpdate.setIncidenceState(incidence.getIncidenceState());
        incidenceRepository.save(incidenceToUpdate);
    }


    private void checkDepartmentAndSpace(FullIncidenceDTO fullIncidenceDTO) {
        long departmentId = fullIncidenceDTO.getDepartment().getId();
        long spaceId = fullIncidenceDTO.getSpace().getId();

        if (departmentId == -1) {
            fullIncidenceDTO.setDepartment(null);
        }

        if (spaceId == -1) {
            fullIncidenceDTO.setSpace(null);
        }
    }

    private FullIncidenceDTO convertToDTO(Incidence incidence) {
        String days = getTotalDays(incidence.getDateStart());
        FullIncidenceDTO fullIncidenceDTO = IncidenceMapper.INSTANCE.incidenceToDTO(incidence);
        fullIncidenceDTO.setDaysAgo(days);
        return fullIncidenceDTO;
    }


    private String getTotalDays(LocalDateTime dateStart){
        Locale locale = LocaleContextHolder.getLocale();
        Period between = Period.between(dateStart.toLocalDate(), LocalDateTime.now().toLocalDate());
        if (between.isZero()){
            if (locale.getLanguage().equals("es")) {
                return  "Hoy";
            } else {
                return  "Today";
            }
        }else {
            return (locale.getLanguage().equals("es") ? "Hace " : "" +
                    Integer.toString(between.getDays()) + " " +
                    (locale.getLanguage().equals("es") ? " días" : " days ago"));
        }
    }
}
