package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.configuration.PasswordEncoderConf;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.UserDataDTO;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserData;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.mapper.UserMapper;
import com.quicksolve.proyecto.mapper.UserDataMapper;
import com.quicksolve.proyecto.repository.UserDataRepository;
import com.quicksolve.proyecto.repository.UserRepository;
import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserDataRepository userDataRepo;
    @Autowired
    private PasswordEncoderConf passwordEncoder;

    public FullUserDTO createUser(FullUserDTO usr){
        usr.setActive(true);
        usr.setType(UserType.USER);
        usr.setPassword(passwordEncoder.encoder().encode(usr.getPassword()));

        User userToSave = UserMapper.INSTANCE.DTOtoUser(usr);
        UserData dataToSave = UserDataMapper.INSTANCE.DTOtoUserData(usr.getData());
        dataToSave.setUser(userToSave);

        userRepo.save(userToSave);
        userDataRepo.save(dataToSave);

        FullUserDTO userInserted = getUserBy(usr.getEmail());
        userInserted.setData(UserDataMapper.INSTANCE.dataToDTO(userDataRepo.findByUserId(userInserted.getId())));
        return userInserted;
    }

    public FullUserDTO updateUser(FullUserDTO usr){
        System.out.println(usr.getId());
        return new FullUserDTO();
    }

    public FullUserDTO getUserBy(Long id){
        return UserMapper.INSTANCE.userToDTO(userRepo.getReferenceById(id));
    }
    public FullUserDTO getUserBy(String email){
        return UserMapper.INSTANCE.userToDTO(userRepo.findByEmail(email));
    }

    public boolean existsWithUsername(String username){
        return userRepo.existsByUsername(username);
    }

    public boolean existsWithEmail(String email){
        return userRepo.existsByEmail(email);
    }

    public FullUserDTO getFullUser(Long id){
        UserData userData = userDataRepo.getReferenceById(id);

        UserDataDTO data = UserDataMapper.INSTANCE.dataToDTO(userData);
        FullUserDTO usr = UserMapper.INSTANCE.userToDTO(userData.getUser());

        usr.setData(data);
        return usr;
    }
}
