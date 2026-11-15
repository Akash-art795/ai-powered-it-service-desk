package com.project.service_desk.dto;

import com.project.service_desk.enums.Roles;

import java.util.Set;

public class LoginResponseDTO {

    private final String message;
    private final String token;
    private final String email;
    private final Set<Roles> roles;

    public LoginResponseDTO(String message, String token, String email, Set <Roles> roles) {
        this.message = message;
        this.token = token;
        this.email = email;
        this.roles = roles;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public Set<Roles> getRoles() {
        return roles;
    }
}
