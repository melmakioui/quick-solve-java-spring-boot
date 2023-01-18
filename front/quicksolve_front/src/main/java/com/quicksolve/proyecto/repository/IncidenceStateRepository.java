package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.IncidenceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenceStateRepository extends JpaRepository<IncidenceState, Long> {

}
