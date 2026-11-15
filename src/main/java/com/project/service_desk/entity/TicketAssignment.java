package com.project.service_desk.entity;

import com.project.service_desk.enums.AssignmentStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ticket_assignments")
public class TicketAssignment {

    @ManyToOne
    @JoinColumn(name = "Ticket_Id",referencedColumnName = "ID")
    private Ticket ticket;
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @ManyToOne
    @JoinColumn(name = "Technician_Id",referencedColumnName = "ID")
    private User technician;
    public User getTechnician() {
        return technician;
    }

    public void setTechnician(User technician) {
        this.technician = technician;
    }
    @ManyToOne
    @JoinColumn(name = "Assigned_By",referencedColumnName = "ID")
    private User assignedBy;
    public void setAssignedBy(User assignedBy) {
        this.assignedBy = assignedBy;
    }
    public User getAssignedBy() {
        return assignedBy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long assignedTicketId;
    @Column(name = "assigned_At")
    private Date assignedAt;
    @Column(name = "ended_At")
    private Date endedAt;
    @Column(name = "assigned_Status")
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    public long getAssignedTicketId() {
        return assignedTicketId;
    }

    public void setAssignedTicketId(long assignedTicketId) {
        this.assignedTicketId = assignedTicketId;
    }

    public Date getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Date assignedAt) {
        this.assignedAt = assignedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }

    public AssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }
}
