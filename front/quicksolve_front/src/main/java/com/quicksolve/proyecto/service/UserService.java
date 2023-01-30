package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.FullUserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    FullUserDTO createUser(FullUserDTO usr);

    FullUserDTO getUserBy(Long id);
    FullUserDTO getUserBy(String email);

    boolean existsWithUsername(String username);

    boolean existsWithEmail(String email);

    FullUserDTO getFullUser(Long id);
    FullUserDTO updateUser(FullUserDTO usr);
}
