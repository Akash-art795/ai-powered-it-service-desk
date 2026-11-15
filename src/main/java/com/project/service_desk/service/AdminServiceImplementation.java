package com.project.service_desk.service;

import com.project.service_desk.entity.User;
import com.project.service_desk.enums.Roles;
import com.project.service_desk.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceImplementation implements AdminService{
    private final UserRepository repo;


    public AdminServiceImplementation(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public Set<Roles> promoteUserToTechnician(Integer Id) {
        Optional<User>userId = repo.findById(Id);
        if(userId.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        Set<Roles> role = userId.get().getRoles();
        if(role.contains(Roles.TECHNICIAN)){
            throw new IllegalStateException("User is already a Technician");
        }

        role.add(Roles.TECHNICIAN);
        repo.save(userId.get());
        return role;
    }
}
