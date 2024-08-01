package com.alm.controllers;

import com.alm.dtos.paginations.UsersPaginationDTO;
import com.alm.dtos.users.CreateUserDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.services.abstractions.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<UsersPaginationDTO> findAllUsers(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        UsersPaginationDTO users = userService.findAllUsers(pageNumber, pageSize);
        return ResponseEntity.ok(users);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<GetUserDTO> findUserById(@PathVariable UUID userId) {
        var user = userService.findUserById(userId);
        return ResponseEntity.ok(user);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<GetUserDTO> updateUser(@PathVariable UUID userId, @RequestBody CreateUserDTO createUserDTO) {
        var user = userService.updateUser(userId, createUserDTO);
        return ResponseEntity.ok(user);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();}
}
