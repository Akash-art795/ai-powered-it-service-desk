package com.project.service_desk.service;

import com.project.service_desk.dto.TicketRequestDTO;
import com.project.service_desk.dto.TicketResponseDTO;
import com.project.service_desk.dto.TicketStatusUpdateDTO;

import java.util.List;

public interface    TicketService {
    List<TicketResponseDTO>getAllTickets();
    TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO);
    TicketResponseDTO getTicketById(long id);
    TicketResponseDTO updateTicket(long id, TicketRequestDTO ticket);
    TicketResponseDTO updateTicketByStatus(long id ,TicketStatusUpdateDTO ticketStatus);
    void deleteTicketById(long id);
}
