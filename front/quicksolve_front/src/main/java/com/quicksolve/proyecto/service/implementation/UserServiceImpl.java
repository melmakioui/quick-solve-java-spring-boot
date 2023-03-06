package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.configuration.PasswordEncoderConf;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.UserDataDTO;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserData;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.mapper.UserMapper;
import com.quicksolve.proyecto.mapper.UserDataMapper;
import com.quicksolve.proyecto.repository.ServiceRepository;
import com.quicksolve.proyecto.repository.UserDataRepository;
import com.quicksolve.proyecto.repository.UserRepository;
import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ServiceRepository serviceRepo;
    @Autowired
    private UserDataRepository userDataRepo;
    @Autowired
    private PasswordEncoderConf passwordEncoder;

    @Override
    public FullUserDTO createUser(FullUserDTO usr){
        usr.setType(UserType.USER);

        if (!usr.isOauth()){
            usr.setActive(false);
            usr.setPassword(passwordEncoder.encoder().encode(usr.getPassword()));
        }

        User userToSave = UserMapper.INSTANCE.DTOtoUser(usr);
        UserData dataToSave = UserDataMapper.INSTANCE.DTOtoUserData(usr.getData());
        dataToSave.setUser(userToSave);

        userRepo.save(userToSave);
        userDataRepo.save(dataToSave);

        FullUserDTO userInserted = getUserBy(usr.getEmail());
        userInserted.setData(UserDataMapper.INSTANCE.dataToDTO(userDataRepo.findByUserId(userInserted.getId())));
        return userInserted;
    }

    @Override
    public FullUserDTO updateUser(FullUserDTO usr){
        User usrToUpdate = userRepo.findByEmail(usr.getEmail());
        UserData usrDataToUpdate = userDataRepo.findByUserId(usrToUpdate.getId());

        usrToUpdate.setUsername(usr.getUsername());
        usrDataToUpdate.setName(usr.getData().getName());
        usrDataToUpdate.setFirstSurname(usr.getData().getFirstSurname());
        usrDataToUpdate.setSecondSurname(usr.getData().getSecondSurname());

        userRepo.save(usrToUpdate);
        userDataRepo.save(usrDataToUpdate);
        return getFullUser(usrToUpdate.getId());
    }

    @Override
    public FullUserDTO updateService(String email, Long idService){
        User usrToUpdate = userRepo.findByEmail(email);
        usrToUpdate.setService(null);
        if (idService != 0){
            usrToUpdate.setService(serviceRepo.getReferenceById(idService));
        }
        userRepo.save(usrToUpdate);

        return getFullUser(usrToUpdate.getId());
    }

    @Override
    public FullUserDTO getUserBy(Long id){
        return UserMapper.INSTANCE.userToDTO(userRepo.getReferenceById(id));
    }
    @Override
    public FullUserDTO getUserBy(String email){
        return UserMapper.INSTANCE.userToDTO(userRepo.findByEmail(email));
    }
    @Override
    public boolean existsWithUsername(String username){
        return userRepo.existsByUsername(username);
    }
    @Override
    public boolean existsWithEmail(String email){
        return userRepo.existsByEmail(email);
    }
    @Override
    public FullUserDTO getFullUser(Long id){
        UserData userData = userDataRepo.getReferenceById(id);

        UserDataDTO data = UserDataMapper.INSTANCE.dataToDTO(userData);
        FullUserDTO usr = UserMapper.INSTANCE.userToDTO(userData.getUser());

        usr.setData(data);
        return usr;
    }

    @Override
    public void activateUser(String email) {
        User usr = userRepo.findByEmail(email);
        usr.setActive(true);
        userRepo.save(usr);
    }

    @Override
    public void changePassword(String email, String password) {
        User usr = userRepo.findByEmail(email);
        usr.setPassword(passwordEncoder.encoder().encode(password));
        userRepo.save(usr);
    }

    @Override
    public List<FullUserDTO> listTechs() {
        List <User> techs = userRepo.findAllByType(UserType.TECH);
        return techs.stream().map(UserMapper.INSTANCE::userToDTO).collect(Collectors.toList());
    }

    @Override
    public FullUserDTO getUserByEmailOrUsername(String emailOrUsername) {
        User usr = userRepo.getUserByEmailOrUsername(emailOrUsername);
        return UserMapper.INSTANCE.userToDTO(usr);
    }
}
