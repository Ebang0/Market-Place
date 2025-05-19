package com.marketplace.Message.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long Id;
    private Long userId1;
    private Long userId2;
    private String description;
    private LocalDateTime date;
    private Status status;


    public enum Status
    {
        SEND,
        RECEIVE,
        OK
    }
    
}
