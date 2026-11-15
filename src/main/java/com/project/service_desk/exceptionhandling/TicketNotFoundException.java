package com.project.service_desk.exceptionhandling;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message){
        super(message);
    }
}
