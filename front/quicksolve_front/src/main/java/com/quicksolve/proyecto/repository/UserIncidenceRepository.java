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
    List<UserIncidence> findAllByTech(User user);
    void deleteByIncidenceId(long id);
    UserIncidence findByIncidenceId(long id);
    UserIncidence findByIncidenceIdAndUserId(long incidenceId, long userId);

    @Query("SELECT tech FROM UserIncidence WHERE tech IS NOT NULL GROUP BY tech ORDER BY COUNT(tech) ASC LIMIT 1")
    User findByLessIncidencesTech();
    @Query("SELECT tech FROM UserIncidence WHERE tech.department.id = ?1 AND tech IS NOT NULL GROUP BY tech ORDER BY COUNT(tech) ASC LIMIT 1")
    User findByLessIncidencesTech(long departmentId);
    @Query("SELECT u FROM UserIncidence u WHERE u.incidence.id = ?1 AND u.tech.id = ?2")
    UserIncidence findByIncidenceIdAndTechId (long incidenceId, long techId);
}
