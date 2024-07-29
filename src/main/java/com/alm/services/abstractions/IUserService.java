package com.alm.services.abstractions;

import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<GetUserDTO> findAllUsers();
    GetUserDTO findUserById(UUID userId);
    GetUserDTO updateUser(UUID userId, CreateUserDTO createUserDTO);
    void deleteUser(UUID userId);
}
