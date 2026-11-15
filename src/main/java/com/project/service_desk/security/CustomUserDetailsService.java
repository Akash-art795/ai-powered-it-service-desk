package com.project.service_desk.security;

import com.project.service_desk.entity.User;
import com.project.service_desk.enums.Roles;
import com.project.service_desk.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Email Not Found"));

        String userEmail = user.getEmail();
        String password = user.getPassword();

        Set<Roles> roles = user.getRoles();

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User
                .withUsername(userEmail)
                .password(password)
                .authorities(authorities)
                .build();
    }
}