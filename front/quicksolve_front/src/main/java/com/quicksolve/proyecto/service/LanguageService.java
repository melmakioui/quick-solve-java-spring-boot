package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.LanguageDTO;

import java.util.List;

public interface LanguageService {

    List<LanguageDTO> getLanguage(String language);

    List<LanguageDTO> listLanguages();
}
