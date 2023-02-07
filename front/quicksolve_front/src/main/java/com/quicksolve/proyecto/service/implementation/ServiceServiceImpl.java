package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.AdvantageDTO;
import com.quicksolve.proyecto.dto.ServiceDTO;
import com.quicksolve.proyecto.entity.Advantage;
import com.quicksolve.proyecto.entity.AdvantageLanguage;
import com.quicksolve.proyecto.mapper.AdvantageMapper;
import com.quicksolve.proyecto.mapper.ServiceMapper;
import com.quicksolve.proyecto.repository.AdvantageLanguageRepository;
import com.quicksolve.proyecto.repository.AdvantageRepository;
import com.quicksolve.proyecto.repository.LanguageRepository;
import com.quicksolve.proyecto.repository.ServiceRepository;
import com.quicksolve.proyecto.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceRepository serviceRepo;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    AdvantageLanguageRepository advantageLanguageRepo;

    @Autowired
    AdvantageRepository advantageRepo;

    @Override
    public List<ServiceDTO> showAll() {
        return serviceRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Long getLastService(){
        return serviceRepo.findTopByOrderByIdDesc().getId();
    }

    private ServiceDTO convertToDTO(com.quicksolve.proyecto.entity.Service service){
        ServiceDTO serviceDTO = ServiceMapper.INSTANCE.serviceToDTO(service);

        List<AdvantageDTO> advs = new ArrayList<>();
        for (Advantage adv : service.getAdvantages()){
            advs.add(convertAdvantageToDTO(adv));
        }
        serviceDTO.setAdvantages(advs);
        return serviceDTO;
    }

    private AdvantageDTO convertAdvantageToDTO(Advantage advantage){
        Locale locale = LocaleContextHolder.getLocale();
        long languageId = languageRepository.findByName(locale.getLanguage()).getId();

        AdvantageLanguage advantageLanguage = advantageLanguageRepo.findByAdvantageIdAndLanguageId(
                advantage.getId(),
                languageId
        );

        return AdvantageMapper.INSTANCE.advantageToDTO(advantageLanguage);
    }
}
