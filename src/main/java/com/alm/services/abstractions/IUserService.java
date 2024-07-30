package com.alm.services.abstractions;

import com.alm.dtos.paginations.UsersPaginationDTO;
import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.exceptions.CustomRunTimeException;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UsersPaginationDTO findAllUsers(int pageNumber, int pageSize);
    GetUserDTO findUserById(UUID userId) throws CustomRunTimeException;
    GetUserDTO updateUser(UUID userId, CreateUserDTO createUserDTO);
    void deleteUser(UUID userId);
}
