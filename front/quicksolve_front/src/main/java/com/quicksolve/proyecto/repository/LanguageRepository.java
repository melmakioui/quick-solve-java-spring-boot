package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findByName(String language);
}
