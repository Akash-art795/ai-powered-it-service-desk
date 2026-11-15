package com.project.service_desk.repository;

import com.project.service_desk.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);
}
