package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.ServiceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceState;
import com.quicksolve.proyecto.entity.UserIncidence;
import com.quicksolve.proyecto.entity.type.UserType;
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
    private final Long INCIDENCE_CANCELLED_STATE = 4L;
    private FullIncidenceDTO lastIncidence = null;
    private FullIncidenceDTO lastUpdatedIncidence = null;

    @Autowired
    private IncidenceRepository incidenceRepository;

    @Autowired
    private UserIncidenceRepository userIncidenceRepo;

    @Autowired
    private IncidenceStateRepository incidenceStateRepository;

    @Autowired
    private IncidenceFileRepository incidenceFileRepository;

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
    public List<FullIncidenceDTO> listByAssignedTech(FullUserDTO userDTO){
        List<Incidence> incidences = userIncidenceRepo.findAllByTech(UserMapper.INSTANCE.DTOtoUser(userDTO))
                .stream()
                .map(UserIncidence::getIncidence)
                .toList();
        List<FullIncidenceDTO> incidencesDTO = incidences.stream().map(this::convertToDTO).toList();
        incidencesDTO.forEach(i -> i.setPriority(getPriority(i.getUser())));
        return incidencesDTO;
    }

    private long getPriority(FullUserDTO userDTO){
        return userDTO.getService() == null ? 0L : userDTO.getService().getId();
    }

    @Override
    public FullIncidenceDTO findById(long id) {
        Incidence incidence = incidenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la incidencia"));
        return convertToDTO(incidence);
    }

    @Override
    public void save(FullIncidenceDTO fullIncidenceDTO) {
        Incidence incidence = saveIncidence(fullIncidenceDTO);
        UserIncidence userIncidence = new UserIncidence();
        userIncidence.setIncidence(incidence);
        userIncidenceRepo.save(userIncidence);
        lastIncidence = convertToDTO(incidence);
    }

    @Override
    public void save(FullIncidenceDTO fullIncidenceDTO, FullUserDTO userDTO) {
        Incidence incidence = saveIncidence(fullIncidenceDTO);
        lastIncidence = convertToDTO(incidence);
        UserIncidence userIncidence = new UserIncidence();
        userIncidence.setIncidence(incidence);
        userIncidence.setUser(UserMapper.INSTANCE.DTOtoUser(userDTO));
        userIncidenceRepo.save(userIncidence);
    }

    /**
     * @param fullIncidenceDTO
     * Comprueba si la incidencia tiene espacio y departamento asignado, si no los tiene los asigna null
     * Assigna el estado de la incidencia a "Esperando" y la fecha de creacion a la fecha actual
     * * @return Incidence
     */

    private Incidence saveIncidence(FullIncidenceDTO fullIncidenceDTO) {
        checkDepartmentAndSpace(fullIncidenceDTO);
        Incidence incidence = IncidenceMapper.INSTANCE.dtoToIncidence(fullIncidenceDTO);
        incidence.setDateStart(LocalDateTime.now());
        IncidenceState waitingState = incidenceStateRepository.getReferenceById(INCIDENCE_WAITING_STATE);
        incidence.setIncidenceState(waitingState);
        return incidenceRepository.save(incidence);
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
        //Si se elimina una incidencia la ruta de archivos asociados tambien se eliminan
        incidenceFileRepository.deleteAllByIncidenceId(incidence.getId());
    }

    @Override
    public void cancel(long id) {
        Incidence incidence = incidenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la incidencia"));
        incidence.setIncidenceState(incidenceStateRepository.getReferenceById(INCIDENCE_CANCELLED_STATE));
        incidence.setDateEnd(LocalDateTime.now());
        incidenceRepository.save(incidence);
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
        Incidence updatedIncidence = incidenceRepository.save(incidenceToUpdate);
        lastUpdatedIncidence = convertToDTO(updatedIncidence);
    }

    @Override
    public FullIncidenceDTO getLastIncidence() {
        return lastIncidence;
    }

    @Override
    public FullIncidenceDTO getLastUpdatedIncidence() {
        return lastUpdatedIncidence;
    }

    @Override
    public FullIncidenceDTO findIncidenceByIdAndUserId(long incidenceId, FullUserDTO userDTO) {
        UserIncidence userIncidence = userIncidenceRepo.findByIncidenceIdAndUserId(incidenceId,userDTO.getId());

        if(userDTO.getType() == UserType.TECH && userIncidence == null){
            UserIncidence techIncidence = userIncidenceRepo.findByIncidenceIdAndTechId(incidenceId,userDTO.getId());
            if(techIncidence == null){
                throw new RuntimeException("No esta asignado a esta incidencia");
            }
            return convertToDTO(techIncidence.getIncidence());
        }

        if (userIncidence == null){
            throw new RuntimeException("No es usuario de la incidencia");
        }

        return convertToDTO(userIncidence.getIncidence());
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

        UserIncidence ui = userIncidenceRepo.findByIncidenceId(incidence.getId());

        if (ui == null){
            fullIncidenceDTO.setUser(null);
        }else{
            fullIncidenceDTO.setUser(UserMapper.INSTANCE.userToDTO(ui.getUser()));
        }

        return fullIncidenceDTO;
    }

    private String getTotalDays(LocalDateTime dateStart) {
        Locale locale = LocaleContextHolder.getLocale();
        Period between = Period.between(dateStart.toLocalDate(), LocalDateTime.now().toLocalDate());
        if (between.isZero()) {
            return (locale.getLanguage().equals("es")) ? "Hoy" : "Today";
        } else {
            return (locale.getLanguage().equals("es") ? "Hace " + between.getDays() + " día/s" : between.getDays() + " day/s ago");
        }
    }
}
