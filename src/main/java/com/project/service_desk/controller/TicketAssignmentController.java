package com.project.service_desk.controller;

import com.project.service_desk.dto.TicketAssignmentResponseDTO;
import com.project.service_desk.entity.TicketAssignment;
import com.project.service_desk.service.TicketAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/tickets")
public class TicketAssignmentController {
    private final TicketAssignmentService service;

    public TicketAssignmentController(TicketAssignmentService service) {
        this.service = service;
    }

    @PostMapping("/{ticketId}/assign/{technicianId}")
    public ResponseEntity<TicketAssignmentResponseDTO> assignTicket(@PathVariable long ticketId, @PathVariable Integer technicianId){
        TicketAssignmentResponseDTO assignment = service.assignTicket(ticketId,technicianId);
        return ResponseEntity.ok(assignment);
    }
}
