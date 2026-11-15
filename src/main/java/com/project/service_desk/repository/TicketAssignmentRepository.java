package com.project.service_desk.repository;

import com.project.service_desk.entity.TicketAssignment;
import com.project.service_desk.enums.AssignmentStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketAssignmentRepository extends JpaRepository<TicketAssignment,Long> {
    Optional<TicketAssignment> findByTicket_IdAndStatus(long ticketId, AssignmentStatus status);
    List<TicketAssignment> findByTicket_Id(long ticketId); //managing ticket history
    boolean existsByTicket_IdAndStatus(long ticketId , AssignmentStatus status);  //We need to check whether a ticket already has an ACTIVE assignment before creating another one.
}
