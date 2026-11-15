package com.project.service_desk.entity;

import com.project.service_desk.enums.Priority;
import com.project.service_desk.enums.Status;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="tickets")
public class Ticket {

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "ID")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne
    @JoinColumn(name = "technician_id",referencedColumnName = "ID")
    private User technician;

    public User getTechnician() {
        return technician;
    }

    public void setTechnician(User technician) {
        this.technician = technician;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "Category")
    private String category;
    @Column(name = "Priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "Withdrawal_Reason")
    private String withdrawalReason;
    @Column(name = "Withdrawal_Request_Time")
    private Date withdrawalRequestedAt;
    @Column(name = "Withdrawal_Reviewed_Time")
    private Date withdrawalReviewedAt;
    @Column(name = "Withdrawal_Approval")
    private Boolean withdrawalApproval;
    @Column(name = "Previous_State")
    @Enumerated(EnumType.STRING)
    private Status previousStatus;

    public Ticket(){}

    public Ticket(String title, String description, String category, Priority priority, Status status, String withdrawalReason, Date withdrawalRequestedAt, Date withdrawalReviewedAt, Boolean withdrawalApproval, Status previousStatus) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = status;
        this.withdrawalReason = withdrawalReason;
        this.withdrawalRequestedAt = withdrawalRequestedAt;
        this.withdrawalReviewedAt = withdrawalReviewedAt;
        this.withdrawalApproval = withdrawalApproval;
        this.previousStatus = previousStatus;
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

    @PrePersist
    private void onCreate(){
        createdAt = new Date();
    }

    public long getId(){
        return id;
    }

    public String getWithdrawalReason() {
        return withdrawalReason;
    }

    public void setWithdrawalReason(String withdrawalReason) {
        this.withdrawalReason = withdrawalReason;
    }

    public Date getWithdrawalRequestedAt() {
        return withdrawalRequestedAt;
    }

    public void setWithdrawalRequestedAt(Date withdrawalRequestAt) {
        this.withdrawalRequestedAt = withdrawalRequestAt;
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

    public Status getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(Status previousStatus) {
        this.previousStatus = previousStatus;
    }
}
