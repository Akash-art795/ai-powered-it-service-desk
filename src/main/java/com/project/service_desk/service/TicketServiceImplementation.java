package com.project.service_desk.service;

import com.project.service_desk.dto.TicketRequestDTO;
import com.project.service_desk.dto.TicketResponseDTO;
import com.project.service_desk.dto.TicketStatusUpdateDTO;
import com.project.service_desk.dto.TicketWithdrawalDTO;
import com.project.service_desk.entity.Ticket;
import com.project.service_desk.entity.User;
import com.project.service_desk.enums.Roles;
import com.project.service_desk.enums.Status;
import com.project.service_desk.exceptionhandling.TicketNotFoundException;
import com.project.service_desk.mapper.TicketMapper;
import com.project.service_desk.repository.TicketRepository;
import com.project.service_desk.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TicketServiceImplementation implements TicketService {
    private final TicketRepository repo;
    private final TicketMapper mapper;
    private final UserRepository user;

    TicketServiceImplementation(TicketRepository repo, TicketMapper mapper, UserRepository user) {
        this.repo = repo;
        this.mapper = mapper;
        this.user = user;
    }

    @Override
    public List<TicketResponseDTO> getAllTickets() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userDetails = user.findByEmail(email);

        if(userDetails.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        Set<Roles> roles = userDetails.get().getRoles();
        List<Ticket> result;
        if(roles.contains(Roles.ADMIN)){
            result = repo.findAll();
        } else if (roles.contains(Roles.USER)){
            result = repo.findByUserEmail(email);
        } else if(roles.contains(Roles.TECHNICIAN)){
            result = repo.findByTechnicianEmail(email);
        } else if(roles.contains(Roles.TECHNICIAN) && roles.contains(Roles.USER)){
            Set<Ticket> combinedTickets = new LinkedHashSet<>();
            combinedTickets.addAll(repo.findByUserEmail(email));
            combinedTickets.addAll(repo.findByTechnicianEmail(email));
            result = new ArrayList<>(combinedTickets);
        }
        else{
            throw new AccessDeniedException("Access Denied");
        }
        List<TicketResponseDTO> response = new ArrayList<>();
        for (Ticket ticket : result) {
            response.add(mapper.toResponseDTO(ticket));
        }
        return response;
    }

    @Override
    public TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userDetails = user.findByEmail(email);
        User employee;
        if(userDetails.isPresent()){
            employee = userDetails.get();
        }
        else{
            throw new UsernameNotFoundException("Invalid Email");
        }
        Set<Roles> roles = userDetails.get().getRoles();
        if (!roles.contains(Roles.USER) && !roles.contains(Roles.ADMIN)) {
            throw new AccessDeniedException("You are not authorized to create tickets");
        }
        Ticket ticket = mapper.toEntity(ticketRequestDTO);
        ticket.setUser(employee);
        ticket.setStatus(Status.OPEN);
        Ticket result = repo.save(ticket);
        return mapper.toResponseDTO(result);
    }

    @Override
    public TicketResponseDTO getTicketById(long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userDetails = user.findByEmail(email);
        if (userDetails.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        Optional<Ticket> ticketDetails = repo.findById(id);
        if (ticketDetails.isEmpty()) {
            throw new TicketNotFoundException("Ticket Not Found: " + id);
        }
        Ticket ticket = ticketDetails.get();
        Set<Roles> roles = userDetails.get().getRoles();
        if (roles.contains(Roles.ADMIN)) {
            return mapper.toResponseDTO(ticket);
        }
        boolean isUser = ticket.getUser().getEmail().equals(email);
        boolean isAssignedTechnician = ticket.getTechnician() != null && ticket.getTechnician().getEmail().equals(email);
        if (roles.contains(Roles.USER) && roles.contains(Roles.TECHNICIAN)) {
            if (isUser || isAssignedTechnician) {
                return mapper.toResponseDTO(ticket);
            }
        }
        else if (roles.contains(Roles.USER)) {
            if (isUser) {
                return mapper.toResponseDTO(ticket);
            }
        } else if (roles.contains(Roles.TECHNICIAN)) {
            if(isAssignedTechnician){
                return mapper.toResponseDTO(ticket);
            }
        }
        throw new AccessDeniedException("Access Denied");
    }

    @Override
    public TicketResponseDTO updateTicket(long id, TicketRequestDTO ticketRequest) {
        Optional<Ticket> ticket = repo.findById(id);
        if(ticket.isEmpty()){
            throw new TicketNotFoundException("Ticket Not Found: "+id);
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userDetails = user.findByEmail(email);
        if(userDetails.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        Set<Roles> roles = userDetails.get().getRoles();
        if (roles.contains(Roles.USER)) {
            if (!ticket.get().getUser().getEmail().equals(email)) {
                throw new AccessDeniedException("Access Denied");
            }
        } else if (!roles.contains(Roles.ADMIN)) {
            throw new AccessDeniedException("Access Denied");
        }
        Ticket userTicket = ticket.get();
        userTicket.setTitle(ticketRequest.getTitle());
        userTicket.setDescription(ticketRequest.getDescription());
        userTicket.setPriority(ticketRequest.getPriority());
        userTicket.setCategory(ticketRequest.getCategory());
        return mapper.toResponseDTO(repo.save(userTicket));
    }

    @Override
    public TicketResponseDTO updateTicketByStatus(long id , TicketStatusUpdateDTO ticketStatus) {
        Optional <Ticket> ticket = repo.findById(id);
        if(ticket.isEmpty()){
            throw new TicketNotFoundException("Ticket Not Found : "+id);
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userDetails = user.findByEmail(email);
        if(userDetails.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        Set<Roles> roles = userDetails.get().getRoles();
        if(!(roles.contains(Roles.ADMIN))){
            throw new AccessDeniedException("Access Denied");
        }
        Ticket userTicket = ticket.get();
        if (userTicket.getStatus() == Status.WITHDRAWAL_REQUESTED) {
            throw new IllegalStateException("Cannot update status while withdrawal is pending");
        }
        if (ticketStatus.getStatus() == Status.CANCELLED) {
            throw new IllegalStateException("Use the withdrawal approval process to cancel a ticket");
        }
        if (ticketStatus.getStatus() == Status.WITHDRAWAL_REQUESTED) {
            throw new IllegalStateException("Use the withdrawal request process to request ticket cancellation");
        }
        userTicket.setStatus(ticketStatus.getStatus());
        return mapper.toResponseDTO(repo.save(userTicket));
    }

    @Override
    public void deleteTicketById(long id) {
        Optional <Ticket> ticket = repo.findById(id);
        if(ticket.isEmpty()){
            throw new TicketNotFoundException("Ticket Not Found : "+id);
        }
        repo.delete(ticket.get());
    }

    @Override
    public TicketResponseDTO withdrawTicket(long id,TicketWithdrawalDTO withdrawal) {
        Optional<Ticket> ticket = repo.findById(id);
        if(ticket.isEmpty()){
            throw new TicketNotFoundException("Ticket not Found : "+id);
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userDetails = user.findByEmail(email);
        if(userDetails.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        Set<Roles> roles = userDetails.get().getRoles();
        if(!(roles.contains(Roles.USER))){
            throw new AccessDeniedException("Access Denied,Only User can Withdraw Tickets");
        }
        if(!(ticket.get().getUser().getEmail().equals(email))){
            throw new AccessDeniedException("Access Denied");
        }
        Status status = ticket.get().getStatus();
        if(!(status == Status.OPEN)){
            throw new IllegalStateException("Invalid Operation");
        }
        ticket.get().setPreviousStatus(status);
        ticket.get().setStatus(Status.WITHDRAWAL_REQUESTED);
        ticket.get().setWithdrawalReason(withdrawal.getReason());
        ticket.get().setWithdrawalApproval(null);
        ticket.get().setWithdrawalRequestedAt(new Date());

        return mapper.toResponseDTO(repo.save(ticket.get()));
    }

    @Override
    public TicketResponseDTO approveWithdrawal(long id) {
        Optional<Ticket> ticket = repo.findById(id);
        if(ticket.isEmpty()){
            throw new TicketNotFoundException("Ticket Not Found : "+id);
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userDetails = user.findByEmail(email);
        if(userDetails.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        Set<Roles> roles = userDetails.get().getRoles();
        if(!(roles.contains(Roles.ADMIN))){
            throw new AccessDeniedException("Access Denied");
        }
        if(!(ticket.get().getStatus() == Status.WITHDRAWAL_REQUESTED)){
            throw new IllegalStateException("Invalid Operation");
        }
        ticket.get().setWithdrawalApproval(true);
        ticket.get().setWithdrawalReviewedAt(new Date());
        ticket.get().setStatus(Status.CANCELLED);

        return mapper.toResponseDTO(repo.save(ticket.get()));
    }

    @Override
    public TicketResponseDTO rejectWithdrawal(long id) {
        Optional<Ticket> ticket = repo.findById(id);
        if(ticket.isEmpty()){
            throw new TicketNotFoundException("Ticket Not Found : "+id);
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userDetails = user.findByEmail(email);
        if(userDetails.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        Set<Roles> roles = userDetails.get().getRoles();
        if(!(roles.contains(Roles.ADMIN))){
            throw new AccessDeniedException("Access Denied");
        }
        if(ticket.get().getStatus()!=Status.WITHDRAWAL_REQUESTED){
            throw new IllegalStateException("No pending withdrawal request");
        }
        ticket.get().setStatus(ticket.get().getPreviousStatus());
        ticket.get().setWithdrawalApproval(false);
        ticket.get().setWithdrawalReviewedAt(new Date());
        return mapper.toResponseDTO(repo.save(ticket.get()));
    }
}
