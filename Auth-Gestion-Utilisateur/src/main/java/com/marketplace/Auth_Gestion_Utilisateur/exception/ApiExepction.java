package com.marketplace.Auth_Gestion_Utilisateur.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiExepction {
    private String message;
    private int code;
    private LocalDateTime timestamp;
}
