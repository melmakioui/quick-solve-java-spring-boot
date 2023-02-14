package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.entity.History;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.UserIncidence;
import com.quicksolve.proyecto.repository.HistoryRepository;
import com.quicksolve.proyecto.repository.UserIncidenceRepository;
import com.quicksolve.proyecto.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HistoryServiceImpl implements HistoryService {


    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserIncidenceRepository userIncidenceRepository;

    @Override
    public void saveHistory(long incidenceId) {

        UserIncidence ui = userIncidenceRepository.findByIncidenceId(incidenceId);
        Incidence incidence = ui.getIncidence();

        History history = new History();

        history.setTechId(ui.getTech() == null ? 0L : ui.getTech().getId());
        history.setDepartmentId(incidence.getDepartment() == null ? 0L : incidence.getDepartment().getId());
        history.setSpaceId(incidence.getSpace() == null ? 0L : incidence.getSpace().getId());
        history.setDateEnd(incidence.getDateEnd() == null ? null : incidence.getDateEnd());
        history.setIncidenceId(incidence.getId());
        history.setStateId(incidence.getIncidenceState().getId());
        history.setTitle(incidence.getTitle());
        history.setDescription(incidence.getDescription());
        history.setDateStart(incidence.getDateStart());
        history.setChangeDate(LocalDateTime.now());

        History currentHistory = historyRepository.findFirstByIncidenceIdOrderByChangeDateDesc(incidenceId);

        System.out.println("New history: " + history);
        System.out.println("Last" + currentHistory);


        if (history.equals(currentHistory) ) {
            System.out.println("No se ha guardado el historial");
            return;
        }

        historyRepository.save(history);
    }

}

