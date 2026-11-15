package com.project.service_desk.controller;

import com.project.service_desk.dto.LoginRequestDTO;
import com.project.service_desk.dto.LoginResponseDTO;
import com.project.service_desk.dto.RegisterRequestDTO;
import com.project.service_desk.dto.RegisterResponseDTO;
import com.project.service_desk.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public RegisterResponseDTO registerResponse(@Valid @RequestBody RegisterRequestDTO registerRequest){
        return service.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponseDTO loginResponse(@Valid @RequestBody LoginRequestDTO loginRequest){
        return service.login(loginRequest);
    }
}
