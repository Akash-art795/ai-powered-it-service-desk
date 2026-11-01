package com.project.service_desk.exceptionhanding;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message){
        super(message);
    }
}
