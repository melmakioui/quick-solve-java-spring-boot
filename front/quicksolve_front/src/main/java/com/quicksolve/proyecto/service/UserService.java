package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserData;
import com.quicksolve.proyecto.dto.FullUserDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface UserService {

    User createUserAndReturn(User usr, UserData userData);

    User getUserBy(Long id);
    User getUserBy(String email);

    boolean existsWithUsername(String username);

    boolean existsWithEmail(String email);

    FullUserDTO getFullUser(Long id);
}
