package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.IncidenceStateLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IncidenceStateLanguageRepository extends JpaRepository<IncidenceStateLanguage, Long> {

}
