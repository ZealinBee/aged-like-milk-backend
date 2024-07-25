package com.alm.services.abstractions;

import com.alm.dtos.auth.LoginDTO;
import com.alm.dtos.auth.RegisterDTO;
import com.alm.dtos.auth.TokensDTO;
import com.alm.dtos.users.GetUserDTO;

public interface IAuthService {
    GetUserDTO register(RegisterDTO registerDTO);
    TokensDTO login(LoginDTO loginDTO);
}
