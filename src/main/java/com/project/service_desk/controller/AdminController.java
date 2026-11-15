package com.project.service_desk.controller;

import com.project.service_desk.enums.Roles;
import com.project.service_desk.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminController {
    private final AdminService service;
    public AdminController(AdminService service) {
        this.service = service;
    }

    @PatchMapping("/admin/users/{userId}/technician")
    public ResponseEntity<Set<Roles>>updateRoles(@PathVariable Integer userId){
        Set<Roles> roles = service.promoteUserToTechnician(userId);
        return ResponseEntity.ok(roles);
    }
}
