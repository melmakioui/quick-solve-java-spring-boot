package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.entity.UserIncidence;

public interface UserIncidenceService {
    UserIncidence findByIncidenceId(Long id);
}
