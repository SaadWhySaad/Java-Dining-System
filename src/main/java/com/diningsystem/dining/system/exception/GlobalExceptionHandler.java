package com.diningsystem.dining.system.exception;

import com.diningsystem.dining.system.response.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Message<Object>> badRequestException(BadRequestException ex){
        Message<Object> message = new Message<>();
        message.setMessage(ex.getMessage());
        message.setCode(HttpStatus.BAD_REQUEST.value());
        message.setStatus(HttpStatus.BAD_REQUEST.name());
        return ResponseEntity.status(message.getCode()).body(message);
    }

    protected ResponseEntity<Message<Object>> internalServerErrorException(InternalServerErrorException ex){
        Message<Object> message = new Message<>();
        message.setMessage(ex.getMessage());
        message.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return ResponseEntity.status(message.getCode()).body(message);
    }

    protected ResponseEntity<Message<Object>> entityNotFoundException(EntityNotFoundException ex){
        Message<Object> message = new Message<>();
        message.setMessage(ex.getMessage());
        message.setCode(HttpStatus.NOT_FOUND.value());
        message.setStatus(HttpStatus.NOT_FOUND.name());
        return ResponseEntity.status(message.getCode()).body(message);
    }


}
