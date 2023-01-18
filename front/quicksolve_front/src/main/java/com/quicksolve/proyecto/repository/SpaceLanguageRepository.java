package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.SpaceLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceLanguageRepository extends JpaRepository<SpaceLanguage, Long> {

    List<SpaceLanguage> findAllBySpaceId(Long languageId);
    List<SpaceLanguage> findAllByLanguageId(Long languageId);
    List<SpaceLanguage> findAllBySpaceIdAndLanguageId(Long spaceId, Long languageId);

}
