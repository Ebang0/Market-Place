package com.marketplace.Message.service;

import java.util.List;

import com.marketplace.Message.dto.MessageDtoRequest;
import com.marketplace.Message.dto.MessageDtoResponse;

public interface MessageService {
    public void save (MessageDtoRequest messageDtoRequest);
    public List<MessageDtoResponse> get( Long IdSend,Long IdReseive);
    public List<MessageDtoResponse> GetAll(Long Id);
    public void delete( Long Id);

}
