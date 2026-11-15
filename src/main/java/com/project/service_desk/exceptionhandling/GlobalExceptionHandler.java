package com.project.service_desk.exceptionhandling;

import com.project.service_desk.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String , String> fieldErrors = new HashMap<>();
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        for(FieldError f : ex.getBindingResult().getFieldErrors()){
            fieldErrors.put(f.getField(),f.getDefaultMessage());
        }
        responseDTO.setTimestamp(LocalDateTime.now());
        responseDTO.setStatus(400);
        responseDTO.setError("Bad Request");
        responseDTO.setMessage("Validation Failed");
        responseDTO.setPath(request.getRequestURI());
        responseDTO.setFieldErrors(fieldErrors);
        return responseDTO;
    }
    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleTicketNotFound(TicketNotFoundException notFound,HttpServletRequest request){
       ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setTimestamp(LocalDateTime.now());
        responseDTO.setStatus(404);
        responseDTO.setError("Not Found");
        responseDTO.setMessage(notFound.getMessage());
        responseDTO.setPath(request.getRequestURI());
        responseDTO.setFieldErrors(null);
        return responseDTO;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDTO handleAccessDenied(AccessDeniedException accessDenied,HttpServletRequest request){
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setTimestamp(LocalDateTime.now());
        responseDTO.setStatus(403);
        responseDTO.setError("Forbidden");
        responseDTO.setMessage(accessDenied.getMessage());
        responseDTO.setPath(request.getRequestURI());
        responseDTO.setFieldErrors(null);
        return responseDTO;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequest(HttpMessageNotReadableException NotReadable,HttpServletRequest request){
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setTimestamp(LocalDateTime.now());
        responseDTO.setStatus(400);
        responseDTO.setError("Bad Request");
        responseDTO.setMessage("Invalid Request Body or Status Value");
        responseDTO.setPath(request.getRequestURI());
        responseDTO.setFieldErrors(null);
        return responseDTO;
    }


    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleIllegalState(IllegalStateException ex, HttpServletRequest request) {
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setTimestamp(LocalDateTime.now());
        responseDTO.setStatus(400);
        responseDTO.setError("Bad Request");
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setPath(request.getRequestURI());
        responseDTO.setFieldErrors(null);
        return responseDTO;
    }

}
