package com.alm.mappers;

import com.alm.entities.User;
import com.alm.dtos.users.GetUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    GetUserDTO userToGetUserDTO(User user);
}
