package com.project.service_desk.entity;

import com.project.service_desk.enums.Priority;
import com.project.service_desk.enums.Status;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="tickets")
public class Ticket {
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

    public Ticket(){}

    public Ticket(String title, String description, String category, Priority priority, Status status) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = status;
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

}
