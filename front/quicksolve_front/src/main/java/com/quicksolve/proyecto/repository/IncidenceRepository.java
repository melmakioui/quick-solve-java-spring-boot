package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IncidenceRepository extends JpaRepository<Incidence, Long> {

    List<Incidence> findAllBySpaceId(Long spaceId);
    List<Incidence> findAllByIncidenceState(Long statusId);
}
