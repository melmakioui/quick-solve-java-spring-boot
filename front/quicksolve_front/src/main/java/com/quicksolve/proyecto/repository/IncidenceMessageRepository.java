package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.IncidenceMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenceMessageRepository extends JpaRepository<IncidenceMessage, Long> {

    List<IncidenceMessage> findAllByIncidenceId(long id);
    IncidenceMessage findByIdAndIncidenceIdAndUserId(long messageId, long incidenceId, long userId);
    int countAllByIncidenceId(long id);

}
