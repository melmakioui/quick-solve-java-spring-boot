package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.entity.UserIncidence;
import com.quicksolve.proyecto.repository.UserIncidenceRepository;
import com.quicksolve.proyecto.service.UserIncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIncidenceServiceImpl implements UserIncidenceService {

    @Autowired
    private UserIncidenceRepository userIncidenceRepository;

    @Override
    public UserIncidence findByIncidenceId(Long id) {
        return userIncidenceRepository.findByIncidenceId(id);
    }
}
