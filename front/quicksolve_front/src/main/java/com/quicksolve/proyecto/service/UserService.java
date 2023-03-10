package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.FullUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    List<FullUserDTO> listTechs();

    FullUserDTO getUserByEmailOrUsername(String emailOrUsername);

    void isRecovering(String email, boolean isRecovering);
}
