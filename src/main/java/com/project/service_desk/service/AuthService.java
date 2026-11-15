package com.project.service_desk.service;

import com.project.service_desk.dto.LoginRequestDTO;
import com.project.service_desk.dto.LoginResponseDTO;
import com.project.service_desk.dto.RegisterRequestDTO;
import com.project.service_desk.dto.RegisterResponseDTO;

public interface AuthService {
    RegisterResponseDTO register(RegisterRequestDTO registerRequest);
    LoginResponseDTO login(LoginRequestDTO loginRequest);
}
