package com.alm.services.implementations;

import com.alm.configs.security.JwtService;
import com.alm.dtos.auth.LoginDTO;
import com.alm.dtos.auth.RegisterDTO;
import com.alm.dtos.auth.TokensDTO;
import com.alm.dtos.users.GetUserDTO;
import com.alm.entities.Role;
import com.alm.entities.User;
import com.alm.mappers.UserMapper;
import com.alm.repositories.UserRepo;
import com.alm.services.abstractions.IAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepo userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public GetUserDTO register(RegisterDTO registerDTO) {
        User newUser = new User();
        newUser.setRole(Role.USER);
        newUser.setUsername(registerDTO.getUsername());
        var encodedPassword = passwordEncoder.encode(registerDTO.password);
        newUser.setPassword(encodedPassword);
        newUser.setEmail(registerDTO.getEmail());
        userRepo.save(newUser);

        return userMapper.userToUserDTO(newUser);
    }

    public TokensDTO login(LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getPassword()
        ));
        var user = userRepo.findOneByEmail(loginDTO.getEmail()).orElseThrow();
        var tokens = new TokensDTO();
        tokens.accessToken = jwtService.generateToken(user);
        return tokens;
    }
}
