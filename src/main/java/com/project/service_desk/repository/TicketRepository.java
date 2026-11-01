package com.project.service_desk.repository;

import com.project.service_desk.dto.TicketResponseDTO;
import com.project.service_desk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Optional<Ticket> findTicketById(TicketResponseDTO ticket);
}
