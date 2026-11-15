package com.project.service_desk.entity;

import com.project.service_desk.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;


@Entity
public class User {
    @Id
    @Column(name = "ID",nullable = false)
    private int employeeId;

    @Column(name = "Name",nullable = false)
    @NotBlank
    private String name;

    @Column(name = "Email",unique = true,nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(name = "Password",nullable = false)
    @NotBlank
    private String password;

    @ElementCollection
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID"),
            uniqueConstraints = @UniqueConstraint(name = "uk_user_role", columnNames = {"user_id", "roles"})
    )
    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles = new HashSet<>();

    public User() {
    }

    public User(int employeeId, String name, String email, String password, Set<Roles> roles) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
