package com.project.service_desk.dto;

import com.project.service_desk.enums.Roles;

import java.util.Set;

public class RegisterResponseDTO {
    private Integer employeeId;
    private String name;
    private String email;
    private Set <Roles> roles;

    public RegisterResponseDTO() {
    }

    public RegisterResponseDTO(Integer employeeId, String name, String email, Set <Roles> roles) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}