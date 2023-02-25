package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.UserDataDTO;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserData;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.mapper.UserDataMapper;
import com.quicksolve.proyecto.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    FullUserDTO createUser(FullUserDTO usr);

    FullUserDTO updateUser(FullUserDTO usr);

    FullUserDTO updateService(String email, Long idService);

    FullUserDTO getUserBy(Long id);

    FullUserDTO getUserBy(String email);

    boolean existsWithUsername(String username);

    boolean existsWithEmail(String email);

    FullUserDTO getFullUser(Long id);

    void activateUser(String email);

    void changePassword(String email, String password);
}
