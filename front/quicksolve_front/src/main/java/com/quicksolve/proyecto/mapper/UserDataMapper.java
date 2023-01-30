package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.UserDataDTO;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDataMapper {
    UserDataMapper INSTANCE = Mappers.getMapper(UserDataMapper.class);

    @Mapping(source = "userData.id", target = "id")
    @Mapping(source = "userData.user.id", target = "idUser")
    UserDataDTO dataToDTO (UserData userData);
    UserData DTOtoUserData(UserDataDTO userDataDTO);
    void updateDataFromDto(UserDataDTO dto, @MappingTarget UserData entity);
}
