package com.marketplace.Message.mapper;

import org.springframework.stereotype.Service;

import com.marketplace.Message.dto.MessageDtoRequest;
import com.marketplace.Message.dto.MessageDtoResponse;
import com.marketplace.Message.entity.Message;

@Service
public class Messagemapper {
    public MessageDtoResponse EntityToDto(Message message){
        MessageDtoResponse messageDtoResponse=new MessageDtoResponse(message.getId(),message.getUserId1(), message.getUserId2(),message.getDescription(),message.getDate(),message.getStatus());
        return messageDtoResponse;
    }

    public Message DtoToEntity( MessageDtoRequest messageDtoRequest){
       Message message=new Message(null,messageDtoRequest.userId1(),messageDtoRequest.userId2(),messageDtoRequest.description(),null,null) ;
       return message;
    }
}
