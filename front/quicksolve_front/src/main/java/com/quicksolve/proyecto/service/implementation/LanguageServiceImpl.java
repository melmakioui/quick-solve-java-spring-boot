package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.LanguageDTO;
import com.quicksolve.proyecto.entity.WebPage;
import com.quicksolve.proyecto.repository.LanguageRepository;
import com.quicksolve.proyecto.repository.WebPageLanguageRepository;
import com.quicksolve.proyecto.repository.WebPageRepository;
import com.quicksolve.proyecto.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private WebPageLanguageRepository webPageLanguageRepository;

    @Override
    public List<LanguageDTO> getLanguage(String language) {

        Long languageId = languageRepository.findByName(language).getId();

        return webPageLanguageRepository.findAllByLanguageId(languageId)
                .stream().map(webPageLanguage -> {
                    WebPage webPage = webPageLanguage.getWebPage();
                    LanguageDTO languageDTO = new LanguageDTO();
                    languageDTO.setKey(webPage.getKeyy());
                    languageDTO.setValue(webPageLanguage.getContent());
                    return languageDTO;
                }).toList();
    }
}
