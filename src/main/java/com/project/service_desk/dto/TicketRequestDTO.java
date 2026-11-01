package com.project.service_desk.dto;

import com.project.service_desk.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TicketRequestDTO {
    @NotBlank(message = "Ticket Title is Required")
    private String title;
    @NotBlank(message = "Ticket Description is Required")
    private String description;
    @NotBlank(message = "Ticket Category is Required")
    private String category;
    @NotNull(message = "Ticket Priority is Required")
    private Priority priority;

    public TicketRequestDTO(String title, String description, String category, Priority priority) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
    }

    public TicketRequestDTO(){}

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
}
