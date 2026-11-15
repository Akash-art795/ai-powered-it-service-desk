package com.project.service_desk.repository;

import com.project.service_desk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket>findByUserEmail(String email);
    List<Ticket>findByTechnicianEmail(String email);

}
