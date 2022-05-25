package com.arkadiuszbanas.app.backend.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> userNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<String> userAlreadyExists(EntityExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> invalidArgs(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}