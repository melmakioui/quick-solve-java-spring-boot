package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.IncidenceStateLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IncidenceStateLanguageRepository extends JpaRepository<IncidenceStateLanguage,Long> {

    List<IncidenceStateLanguage> findAllByLanguageId(Long languageId);
    List<IncidenceStateLanguage> findAllByStatusIdAndLanguageId(Long languageId, Long statusId);
    IncidenceStateLanguage findByStatusIdAndLanguageId(Long languageId, Long statusId);
}
