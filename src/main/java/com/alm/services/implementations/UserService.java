package com.alm.services.implementations;

import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.entities.Role;
import com.alm.mappers.UserMapper;
import com.alm.repositories.UserRepo;
import com.alm.entities.User;
import com.alm.services.abstractions.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public List<GetUserDTO> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(user -> {
                    return userMapper.userToUserDTO(user);
                }).collect(Collectors.toList());
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
