package com.project.service_desk.dto;

import jakarta.validation.constraints.NotBlank;

public class TicketWithdrawalDTO {
    @NotBlank(message = "Withdrawal reason is mandatory")
    private String reason;

    public TicketWithdrawalDTO(){

    }

    public TicketWithdrawalDTO(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
