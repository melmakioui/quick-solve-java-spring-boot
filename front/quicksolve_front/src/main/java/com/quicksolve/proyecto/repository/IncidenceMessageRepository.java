package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.IncidenceMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenceMessageRepository extends JpaRepository<IncidenceMessage,Long> {
    List<IncidenceMessage> findAllByIncidenceIdAndUserIdOrderByOrderrDesc(long incidenceId, long userId);
    List<IncidenceMessage> findAllByIncidenceIdOrderByOrderrDesc(long incidenceId);
    int countByIncidenceId(long incidenceId);
    void deleteByIdAndIncidenceIdAndUserId (long incidenceMessageId, long incidenceId, long userId);

    @Modifying
    @Query("DELETE FROM IncidenceMessage im WHERE im.id = ?1 AND im.incidence.incidence.id = ?2 AND im.tech.tech.id = ?3")
    void deleteByIdAndIncidenceIdAndTechId (long incidenceMessageId, long incidenceId, long techId);

    IncidenceMessage findByIdAndUserId (long incidenceMessageId, long userId);

    @Query("SELECT im FROM IncidenceMessage im WHERE im.id = ?1 AND im.tech.tech.id = ?2")
    IncidenceMessage findMessageByTech (long incidenceMessageId, long techId);

}
