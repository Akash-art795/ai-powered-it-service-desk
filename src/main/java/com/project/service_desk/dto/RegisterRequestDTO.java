package com.project.service_desk.dto;

import jakarta.validation.constraints.*;

public class RegisterRequestDTO {
    @Positive
    @NotNull(message = "Valid Employee Id is Required")
    private Integer employeeId;
    @NotBlank(message = "Valid Employee Name is Required")
    private String name;
    @NotBlank(message = "Valid Mail Id is Required")
    @Email(message = "Enter Proper Email id")
    private String email;
    @NotBlank(message = "Valid password is Required")
    @Size(min = 6 , message = "Password minimum length 6")
    private String password;

    public RegisterRequestDTO() {
    }

    public RegisterRequestDTO(Integer employeeId, String name, String email, String password) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
