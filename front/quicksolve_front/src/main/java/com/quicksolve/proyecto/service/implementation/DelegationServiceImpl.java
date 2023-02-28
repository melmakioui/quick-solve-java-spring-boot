package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserIncidence;
import com.quicksolve.proyecto.repository.UserIncidenceRepository;
import com.quicksolve.proyecto.repository.UserRepository;
import com.quicksolve.proyecto.service.DelegationService;
import com.quicksolve.proyecto.service.UserIncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DelegationServiceImpl implements DelegationService {

    @Autowired
    private UserIncidenceRepository userIncidenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void assignTechToIncidence(long incidenceId, long techId) {
        UserIncidence userIncidence = userIncidenceRepository.findByIncidenceId(incidenceId);
        User tech = userRepository.findById(techId).orElseThrow( () -> new RuntimeException("No existe el tecnico"));
        userIncidence.setTech(tech);
        userIncidenceRepository.save(userIncidence);
    }
}
