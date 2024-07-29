package com.alm.controllers;

import com.alm.dtos.auth.LoginDTO;
import com.alm.dtos.auth.RegisterDTO;
import com.alm.dtos.auth.TokensDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.services.implementations.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<GetUserDTO> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<TokensDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {
        TokensDTO tokens = authService.login(loginDTO);
        return ResponseEntity.ok(tokens);
    }
}
