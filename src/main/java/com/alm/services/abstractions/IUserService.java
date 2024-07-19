package com.alm.services.abstractions;

import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;

import java.util.List;

public interface IUserService {
    List<GetUserDTO> findAllUsers();
    GetUserDTO registerUser(CreateUserDTO createUserDTO);
}
