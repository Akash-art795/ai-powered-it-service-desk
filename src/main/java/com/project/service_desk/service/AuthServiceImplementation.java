package com.project.service_desk.service;

import com.project.service_desk.dto.LoginRequestDTO;
import com.project.service_desk.dto.LoginResponseDTO;
import com.project.service_desk.dto.RegisterRequestDTO;
import com.project.service_desk.dto.RegisterResponseDTO;
import com.project.service_desk.entity.User;
import com.project.service_desk.enums.Roles;
import com.project.service_desk.exceptionhandling.EmailAlreadyExistsException;
import com.project.service_desk.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImplementation implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO registerRequest) {
        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if(existingUser.isPresent()){
            throw new EmailAlreadyExistsException("Email is already Registered");
        }
        else{
            User user = new User();
            Set<Roles>roles = new HashSet<>();

            user.setEmployeeId(registerRequest.getEmployeeId());
            user.setName(registerRequest.getName());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            roles.add(Roles.USER);
            user.setRoles(roles);
            User savedUser = userRepository.save(user);

            RegisterResponseDTO response = new RegisterResponseDTO();
            response.setEmployeeId(savedUser.getEmployeeId());
            response.setName(savedUser.getName());
            response.setEmail(savedUser.getEmail());
            response.setRoles(savedUser.getRoles());
            return response;
        }
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest){
        Optional<User> existingUser = userRepository.findByEmail(loginRequest.getEmail());
        if(existingUser.isEmpty()){
            throw new RuntimeException("Invalid Email or Password");
        }
        User user = existingUser.get();
        boolean passwordMatches = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );
        if(!passwordMatches){
            throw new RuntimeException("Invalid email or Password");
        }
        return new LoginResponseDTO("Login Successful", jwtService.generateToken(user.getEmail()), user.getEmail(),user.getRoles());
    }
}
