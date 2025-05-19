package com.marketplace.Message.dto;

import java.time.LocalDateTime;

import com.marketplace.Message.entity.Message.Status;

public record MessageDtoResponse(Long Id, Long userId1, Long userId2, String description,LocalDateTime date,Status status) {

}
