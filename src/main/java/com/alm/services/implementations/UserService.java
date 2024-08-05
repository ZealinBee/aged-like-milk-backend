package com.alm.services.implementations;

import com.alm.dtos.paginations.UsersPaginationDTO;
import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.entities.Role;
import com.alm.exceptions.CustomRunTimeException;
import com.alm.mappers.UserMapper;
import com.alm.repositories.UserRepo;
import com.alm.entities.User;
import com.alm.services.abstractions.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public UsersPaginationDTO findAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> users = userRepo.findAll(pageable);
        List<User> usersList = users.getContent();
        List<GetUserDTO> mappedUsers = usersList.stream()
                .map(userMapper::userToGetUserDTO).toList();

        UsersPaginationDTO usersPaginationDTO = new UsersPaginationDTO(
                users.getNumber(),
                users.getSize(),
                users.getTotalPages(),
                users.getTotalElements(),
                users.isLast(),
                mappedUsers
        );

        return usersPaginationDTO;
    }

    public GetUserDTO findUserById(UUID userId) throws CustomRunTimeException{
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "User Id was not found"));
        return userMapper.userToGetUserDTO(user);
    }


    public GetUserDTO updateUser(UUID userId, CreateUserDTO createUserDTO) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "User Id was not found"));

        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        userRepo.save(user);

        return userMapper.userToGetUserDTO(user);
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "User Id was not found"));
        userRepo.delete(user);
    }
}
