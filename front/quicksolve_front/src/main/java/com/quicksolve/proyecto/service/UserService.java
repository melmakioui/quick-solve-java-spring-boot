package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.configuration.PasswordEncoderConf;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserData;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.mapper.UserDataMapper;
import com.quicksolve.proyecto.repository.UserDataRepository;
import com.quicksolve.proyecto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserDataRepository userDataRepo;
    @Autowired
    private PasswordEncoderConf passwordEncoder;

    public User createUserAndReturn(User usr, UserData userData){
        usr.setActive(true);
        usr.setType(UserType.USER);
        userData.setCreated(LocalDateTime.now());
        usr.setPassword(passwordEncoder.encoder().encode(usr.getPassword()));

        userRepo.save(usr);
        userDataRepo.save(userData);

        return usr;
    }

    public User getUserBy(Long id){
        return userRepo.getReferenceById(id);
    }
    public User getUserBy(String email){
        return userRepo.findByEmail(email);
    }

    public boolean existsWithUsername(String username){
        return userRepo.existsByUsername(username);
    }

    public boolean existsWithEmail(String email){
        return userRepo.existsByEmail(email);
    }

    public FullUserDTO getFullUser(Long id){
        UserData userData = userDataRepo.getReferenceById(id);
        return UserDataMapper.INSTANCE.userDTO(userData, userData.getUser());
    }
}
