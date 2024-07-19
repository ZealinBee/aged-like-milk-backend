package com.alm.services.implementations;

import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.entities.Role;
import com.alm.entities.User;
import com.alm.mappers.UserMapper;
import com.alm.repositories.UserRepo;
import com.alm.services.abstractions.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public List<GetUserDTO> findAllUsers() {
        return null;
    }

    public GetUserDTO registerUser(CreateUserDTO createUserDTO) {
        User newUser = new User();
        newUser.setRole(Role.USER);
        newUser.setUsername(createUserDTO.getUsername());
        newUser.setPassword(createUserDTO.getPassword());
        newUser.setEmail(createUserDTO.getEmail());
        userRepo.save(newUser);

        return userMapper.userToUserDTO(newUser);
    }
}
