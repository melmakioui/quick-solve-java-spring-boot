package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.Advantage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AdvantageRepository extends JpaRepository<Advantage, Long> {

    Set<Advantage> findAllByServiceId(Long serviceId);
}
