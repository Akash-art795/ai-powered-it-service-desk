package com.project.service_desk.mapper;


import com.project.service_desk.dto.TicketRequestDTO;
import com.project.service_desk.dto.TicketResponseDTO;
import com.project.service_desk.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public Ticket toEntity(TicketRequestDTO ticketRequest){
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setCategory(ticketRequest.getCategory());
        ticket.setPriority(ticketRequest.getPriority());
        return ticket;
    }

    public TicketResponseDTO toResponseDTO(Ticket ticket){
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setId(ticket.getId());
        ticketResponseDTO.setTitle(ticket.getTitle());
        ticketResponseDTO.setDescription(ticket.getDescription());
        ticketResponseDTO.setCategory(ticket.getCategory());
        ticketResponseDTO.setPriority(ticket.getPriority());
        ticketResponseDTO.setStatus(ticket.getStatus());
        ticketResponseDTO.setCreatedAt(ticket.getCreatedAt());
        ticketResponseDTO.setWithdrawalApproval(ticket.getWithdrawalApproval());
        ticketResponseDTO.setWithdrawalReason(ticket.getWithdrawalReason());
        ticketResponseDTO.setWithdrawalRequestedAt(ticket.getWithdrawalRequestedAt());
        ticketResponseDTO.setWithdrawalReviewedAt(ticket.getWithdrawalReviewedAt());
        return ticketResponseDTO;
    }
}
