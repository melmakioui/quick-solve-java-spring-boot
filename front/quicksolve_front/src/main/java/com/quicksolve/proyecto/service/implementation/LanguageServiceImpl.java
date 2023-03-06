package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.LanguageDTO;
import com.quicksolve.proyecto.entity.WebPage;
import com.quicksolve.proyecto.repository.LanguageRepository;
import com.quicksolve.proyecto.repository.WebPageLanguageRepository;
import com.quicksolve.proyecto.repository.WebPageRepository;
import com.quicksolve.proyecto.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LanguageServiceImpl implements LanguageService {

    private final String DEFAULT_LANGUAGE = "es";

    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private WebPageLanguageRepository webPageLanguageRepository;

    @Override
    public List<LanguageDTO> getLanguage(String language) {

        Long languageId = languageRepository.findByName(language).getId();

        if (languageId == null) {
            languageId = languageRepository.findByName(DEFAULT_LANGUAGE).getId();
        }

        return webPageLanguageRepository.findAllByLanguageId(languageId)
                .stream().map(webPageLanguage -> {
                    WebPage webPage = webPageLanguage.getWebPage();
                    LanguageDTO languageDTO = new LanguageDTO();
                    languageDTO.setKey(webPage.getKeyy());
                    languageDTO.setValue(webPageLanguage.getContent());
                    return languageDTO;
                }).toList();
    }

    @Override
    public List<LanguageDTO> listLanguages() {

        return languageRepository.findAll()
                .stream().map(language -> {
                    LanguageDTO languageDTO = new LanguageDTO();
                    languageDTO.setLanguage(language.getLanguage());
                    languageDTO.setName(language.getName());
                    return languageDTO;
                }).toList();
    }
}
