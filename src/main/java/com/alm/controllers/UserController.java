package com.alm.controllers;

import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.services.abstractions.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<GetUserDTO>> findAllUsers() {
        var users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<GetUserDTO> registerUser(@RequestBody CreateUserDTO createUserDTO) {
        var user = userService.registerUser(createUserDTO);
        return ResponseEntity.ok(user);
    }
}
