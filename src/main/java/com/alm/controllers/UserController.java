package com.alm.controllers;

import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.services.abstractions.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<GetUserDTO> findAllUsers() {
        return userService.findAllUsers();
    }
    @PostMapping
    public GetUserDTO registerUser(@RequestBody CreateUserDTO createUserDTO) {
        return userService.registerUser(createUserDTO);
    }
}
