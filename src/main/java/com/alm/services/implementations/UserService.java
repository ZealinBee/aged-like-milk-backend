package com.alm.services.implementations;

import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.entities.Role;
import com.alm.exceptions.CustomRunTimeException;
import com.alm.mappers.UserMapper;
import com.alm.repositories.UserRepo;
import com.alm.entities.User;
import com.alm.services.abstractions.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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

    public GetUserDTO findUserById(UUID userId) {
        User user = userRepo.findById(userId).orElse(null);
        return userMapper.userToUserDTO(user);
    }


    public GetUserDTO updateUser(UUID userId, CreateUserDTO createUserDTO) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "User Id was not found"));

        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        userRepo.save(user);

        return userMapper.userToUserDTO(user);
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "User Id was not found"));
        userRepo.delete(user);
    }
}
