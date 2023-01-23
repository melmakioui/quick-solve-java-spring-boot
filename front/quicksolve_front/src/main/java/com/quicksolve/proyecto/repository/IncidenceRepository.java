package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IncidenceRepository extends JpaRepository<Incidence, Long> {
}
