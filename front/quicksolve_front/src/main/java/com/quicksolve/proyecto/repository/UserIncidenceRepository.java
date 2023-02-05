package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserIncidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserIncidenceRepository extends JpaRepository<UserIncidence, Long> {
    List<UserIncidence> findAllByUser(User user);
    void deleteByIncidenceId(long id);
    UserIncidence findByIncidenceId(long id);
    UserIncidence findByIncidenceIdAndUserId (long incidenceId, long userId);
    @Query("SELECT u FROM UserIncidence u WHERE u.incidence.id = ?1 AND u.tech.id = ?2")
    UserIncidence findByIncidenceIdAndTechId (long incidenceId, long techId);
}
