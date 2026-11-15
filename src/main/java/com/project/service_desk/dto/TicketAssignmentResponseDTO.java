package com.project.service_desk.dto;

import com.project.service_desk.enums.AssignmentStatus;
import java.util.Date;

public class TicketAssignmentResponseDTO {

    private long assignedTicketId;
    private long ticketId;
    private String ticketTitle;

    private Integer technicianId;
    private String technicianName;
    private String technicianEmail;

    private Integer assignedById;
    private String assignedByName;

    private Date assignedAt;
    private Date endedAt;
    private AssignmentStatus status;

    public TicketAssignmentResponseDTO(
            long assignedTicketId,
            long ticketId,
            String ticketTitle,
            Integer technicianId,
            String technicianName,
            String technicianEmail,
            Integer assignedById,
            String assignedByName,
            Date assignedAt,
            Date endedAt,
            AssignmentStatus status) {

        this.assignedTicketId = assignedTicketId;
        this.ticketId = ticketId;
        this.ticketTitle = ticketTitle;
        this.technicianId = technicianId;
        this.technicianName = technicianName;
        this.technicianEmail = technicianEmail;
        this.assignedById = assignedById;
        this.assignedByName = assignedByName;
        this.assignedAt = assignedAt;
        this.endedAt = endedAt;
        this.status = status;
    }

    public long getAssignedTicketId() {
        return assignedTicketId;
    }

    public long getTicketId() {
        return ticketId;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public Integer getTechnicianId() {
        return technicianId;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public String getTechnicianEmail() {
        return technicianEmail;
    }

    public Integer getAssignedById() {
        return assignedById;
    }

    public String getAssignedByName() {
        return assignedByName;
    }

    public Date getAssignedAt() {
        return assignedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public AssignmentStatus getStatus() {
        return status;
    }
}