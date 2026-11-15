package com.project.service_desk.service;

import com.project.service_desk.enums.Roles;

import java.util.Set;

public interface AdminService {
    Set<Roles> promoteUserToTechnician(Integer Id);
}
