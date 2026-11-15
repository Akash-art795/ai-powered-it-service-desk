package com.project.service_desk.dto;

import com.project.service_desk.enums.Priority;
import com.project.service_desk.enums.Status;

import java.util.Date;

public class  TicketResponseDTO {
    private long id;
    private String title;
    private String description;
    private String category;
    private Priority priority;
    private Status status;
    private Date createdAt;
    private String withdrawalReason;
    private Date withdrawalRequestedAt;
    private Date withdrawalReviewedAt;
    private Boolean withdrawalApproval;
    public TicketResponseDTO(){}

    public TicketResponseDTO(long id, String title, String description, String category, Priority priority, Status status, Date createdAt, String withdrawalReason, Date withdrawalRequestedAt, Date withdrawalReviewedAt, Boolean withdrawalApproval) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.withdrawalReason = withdrawalReason;
        this.withdrawalRequestedAt = withdrawalRequestedAt;
        this.withdrawalReviewedAt = withdrawalReviewedAt;
        this.withdrawalApproval = withdrawalApproval;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){this.id = id;}

    public String getWithdrawalReason() {
        return withdrawalReason;
    }

    public void setWithdrawalReason(String withdrawalReason) {
        this.withdrawalReason = withdrawalReason;
    }

    public Date getWithdrawalRequestedAt() {
        return withdrawalRequestedAt;
    }

    public void setWithdrawalRequestedAt(Date withdrawalRequestedAt) {
        this.withdrawalRequestedAt = withdrawalRequestedAt;
    }

    public Date getWithdrawalReviewedAt() {
        return withdrawalReviewedAt;
    }

    public void setWithdrawalReviewedAt(Date withdrawalReviewedAt) {
        this.withdrawalReviewedAt = withdrawalReviewedAt;
    }

    public Boolean getWithdrawalApproval() {
        return withdrawalApproval;
    }

    public void setWithdrawalApproval(Boolean withdrawalApproval) {
        this.withdrawalApproval = withdrawalApproval;
    }
}
