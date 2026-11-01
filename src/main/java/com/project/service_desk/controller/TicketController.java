package com.project.service_desk.controller;

import com.project.service_desk.dto.TicketRequestDTO;
import com.project.service_desk.dto.TicketResponseDTO;
import com.project.service_desk.dto.TicketStatusUpdateDTO;
import com.project.service_desk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {
    private final TicketService service;
    TicketController(TicketService service){
        this.service = service;
    }

    @GetMapping("/tickets")
    public List<TicketResponseDTO> getAllTickets(){
        return service.getAllTickets();
    }

    @PostMapping("/tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponseDTO createTicket(@Valid @RequestBody TicketRequestDTO request){
        return service.createTicket(request);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketResponseDTO>getTicketById(@PathVariable(name = "id") long id){
        TicketResponseDTO response = service.getTicketById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<TicketResponseDTO>updateTicket(@PathVariable(name = "id") long id , @Valid @RequestBody TicketRequestDTO request){
        TicketResponseDTO response = service.updateTicket(id,request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/tickets/{id}/status")
    public ResponseEntity<TicketResponseDTO>updateTicketStatus(@PathVariable(name = "id") long id , @Valid @RequestBody TicketStatusUpdateDTO statusDTO){
        TicketResponseDTO response = service.updateTicketByStatus(id,statusDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable(name = "id") long id){
        service.deleteTicketById(id);
        return ResponseEntity.noContent().build();
    }
}
