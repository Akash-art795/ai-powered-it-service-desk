package com.project.service_desk.service;

import com.project.service_desk.dto.TicketAssignmentResponseDTO;
import com.project.service_desk.entity.Ticket;
import com.project.service_desk.entity.TicketAssignment;
import com.project.service_desk.entity.User;
import com.project.service_desk.enums.AssignmentStatus;
import com.project.service_desk.enums.Roles;
import com.project.service_desk.enums.Status;
import com.project.service_desk.exceptionhandling.TicketNotFoundException;
import com.project.service_desk.repository.TicketAssignmentRepository;
import com.project.service_desk.repository.TicketRepository;
import com.project.service_desk.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class TicketAssignmentServiceImplementation implements TicketAssignmentService {
    private final TicketAssignmentRepository assignmentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketAssignmentServiceImplementation(TicketAssignmentRepository assignmentRepository, TicketRepository ticketRepository, UserRepository userRepository){
        this.assignmentRepository = assignmentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public TicketAssignmentResponseDTO assignTicket(long ticketId, Integer technicianId) {

        Optional<Ticket> assignedTicket = ticketRepository.findById(ticketId);
        if (assignedTicket.isEmpty()) {
            throw new TicketNotFoundException("Ticket Not Found With Id: " + ticketId);
        }
        Optional<User> assignedTechnician = userRepository.findById(technicianId);

        if (assignedTechnician.isEmpty()) {
            throw new UsernameNotFoundException("Technician Not Found with Id: " + technicianId);
        }
        Ticket ticket = assignedTicket.get();
        User technician = assignedTechnician.get();

        Set<Roles> roles = technician.getRoles();
        if (!roles.contains(Roles.TECHNICIAN)) {
            throw new IllegalArgumentException("This Employee is not a Technician");
        }
        if (ticket.getUser().getEmail().equals(assignedTechnician.get().getEmail())) {
            throw new IllegalArgumentException("Technician cannot be assigned their own ticket");
        }

        Status currentStatus = ticket.getStatus();
        if (currentStatus != Status.OPEN &&
                currentStatus != Status.ASSIGNED &&
                currentStatus != Status.IN_PROGRESS) {
            throw new IllegalStateException("Ticket cannot be assigned in its current status");
        }
        String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User admin = userRepository.findByEmail(authenticatedUser).orElseThrow(() -> new UsernameNotFoundException("Admin Not Found"));
        Optional<TicketAssignment> previousAssignment = assignmentRepository.findByTicket_IdAndStatus(ticketId, AssignmentStatus.ACTIVE);
        if (previousAssignment.isPresent()) {
            TicketAssignment previous = previousAssignment.get();
            if(previous.getTechnician().getEmployeeId()==technicianId){
                throw new IllegalStateException("Ticket is Already Assigned to this Technician");
            }
            previous.setStatus(AssignmentStatus.REASSIGNED);
            previous.setEndedAt(new Date());
            assignmentRepository.save(previous);
        }


        TicketAssignment newAssignment = new TicketAssignment();

        newAssignment.setTicket(ticket);
        newAssignment.setTechnician(technician);
        newAssignment.setAssignedBy(admin);
        newAssignment.setAssignedAt(new Date());
        newAssignment.setEndedAt(null);
        newAssignment.setStatus(AssignmentStatus.ACTIVE);

        ticket.setTechnician(technician);

        if (currentStatus == Status.OPEN) {
            ticket.setStatus(Status.ASSIGNED);
        }

        ticketRepository.save(ticket);

        TicketAssignment savedAssignment = assignmentRepository.save(newAssignment);

        return new TicketAssignmentResponseDTO(
                savedAssignment.getAssignedTicketId(),
                savedAssignment.getTicket().getId(),
                savedAssignment.getTicket().getTitle(),
                savedAssignment.getTechnician().getEmployeeId(),
                savedAssignment.getTechnician().getName(),
                savedAssignment.getTechnician().getEmail(),
                savedAssignment.getAssignedBy().getEmployeeId(),
                savedAssignment.getAssignedBy().getName(),
                savedAssignment.getAssignedAt(),
                savedAssignment.getEndedAt(),
                savedAssignment.getStatus()
        );
    }
}
