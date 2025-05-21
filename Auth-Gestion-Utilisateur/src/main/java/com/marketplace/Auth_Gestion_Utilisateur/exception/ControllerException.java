package com.marketplace.Auth_Gestion_Utilisateur.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {
    
    @ExceptionHandler(ExceptionRuntine.class)
    public ResponseEntity<?> ErrorMessage(ExceptionRuntine e)
    {
        ApiExepction api = new ApiExepction();
        api.setMessage(e.getMessage());
        api.setCode(org.springframework.http.HttpStatus.NOT_FOUND.value());
        api.setTimestamp(LocalDateTime.now());
        return ResponseEntity.badRequest().body(api);
    }
}
