package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.AdvantageLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvantageLanguageRepository extends JpaRepository<AdvantageLanguage, Long> {
    AdvantageLanguage findByAdvantageIdAndLanguageId(Long advantageId, Long languageId);
}
