package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.WebPageLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebPageLanguageRepository extends JpaRepository<WebPageLanguage, Long> {

    List<WebPageLanguage> findAllByLanguageId(Long webPageId);
}
