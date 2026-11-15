package com.project.service_desk.service;

import com.project.service_desk.dto.TicketAssignmentResponseDTO;
import com.project.service_desk.entity.TicketAssignment;

public interface TicketAssignmentService {
    TicketAssignmentResponseDTO assignTicket(long ticketId, Integer technicianId);
}
