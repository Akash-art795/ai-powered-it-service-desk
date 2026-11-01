package com.project.service_desk.service;

import com.project.service_desk.dto.TicketRequestDTO;
import com.project.service_desk.dto.TicketResponseDTO;
import com.project.service_desk.dto.TicketStatusUpdateDTO;
import com.project.service_desk.entity.Ticket;
import com.project.service_desk.enums.Status;
import com.project.service_desk.exceptionhanding.TicketNotFoundException;
import com.project.service_desk.mapper.TicketMapper;
import com.project.service_desk.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImplementation implements TicketService {
    private final TicketRepository repo;
    private final TicketMapper mapper;

    TicketServiceImplementation(TicketRepository repo, TicketMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<TicketResponseDTO> getAllTickets() {
        List<Ticket> result = repo.findAll();
        List<TicketResponseDTO> response = new ArrayList<>();
        for (Ticket r : result) {
            response.add(mapper.toResponseDTO(r));
        }
        return response;
    }

    @Override
    public TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = mapper.toEntity(ticketRequestDTO);
        ticket.setStatus(Status.OPEN);
        Ticket result = repo.save(ticket);
        return mapper.toResponseDTO(result);
    }

    @Override
    public TicketResponseDTO getTicketById(long id) {
        Optional<Ticket> a = repo.findById(id);
        if (a.isPresent()) {
            return mapper.toResponseDTO(a.get());
        } else {
            throw new TicketNotFoundException("Ticket Not Found: " + id);
        }

    }

    @Override
    public TicketResponseDTO updateTicket(long id, TicketRequestDTO ticketRequest) {
        Optional<Ticket> ticket = repo.findById(id);
        if (ticket.isPresent()) {
            Ticket t = ticket.get();
            t.setTitle(ticketRequest.getTitle());
            t.setDescription(ticketRequest.getDescription());
            t.setPriority(ticketRequest.getPriority());
            t.setCategory(ticketRequest.getCategory());
            return mapper.toResponseDTO(repo.save(t));
        } else {
            throw new TicketNotFoundException("Ticket Not Found: " + id);
        }
    }

    @Override
    public TicketResponseDTO updateTicketByStatus(long id , TicketStatusUpdateDTO ticketStatus) {
        Optional <Ticket> ticket = repo.findById(id);
        if(ticket.isPresent()){
            Ticket t = ticket.get();
            t.setStatus(ticketStatus.getStatus());
            return mapper.toResponseDTO(repo.save(t));
        }
        else{
            throw new TicketNotFoundException("Ticket Not Found"+id);
        }
    }

    @Override
    public void deleteTicketById(long id) {
        Optional <Ticket> ticket = repo.findById(id);
        if(ticket.isPresent()){
            Ticket t = ticket.get();
            repo.deleteById(t.getId());
        }
        else{
            throw new TicketNotFoundException("Ticket Not Found "+id);
        }
    }
}
