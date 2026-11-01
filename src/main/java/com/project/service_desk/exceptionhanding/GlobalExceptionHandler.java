package com.project.service_desk.exceptionhanding;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> ValidationErrorHandling(MethodArgumentNotValidException ex){
        Map<String , String> exception = new HashMap<>();
        for(FieldError f : ex.getBindingResult().getFieldErrors()){
            exception.put(f.getField(),f.getDefaultMessage());
        }
        return exception;
    }
    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String HandleTicketNotFoundException(TicketNotFoundException notFound){
       return notFound.getMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String HandleBaRequest(HttpMessageNotReadableException NotReadable){
        return NotReadable.getMessage();
    }
}
