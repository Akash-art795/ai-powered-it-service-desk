package com.project.service_desk.dto;

import com.project.service_desk.enums.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TicketStatusUpdateDTO {
    @NotNull(message = "Status Field is Null,Please Enter the Status")
    private Status status;
    public void setStatus(Status status){
        this.status = status;
    }
    public Status getStatus(){
        return status;
    }
}
