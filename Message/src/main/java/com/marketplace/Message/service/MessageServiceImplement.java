package com.marketplace.Message.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.marketplace.Message.CustomService.UtilisateurService;
import com.marketplace.Message.dto.MessageDtoRequest;
import com.marketplace.Message.dto.MessageDtoResponse;
import com.marketplace.Message.dto.UtilisateurDtoProfile;
import com.marketplace.Message.entity.Message;
import com.marketplace.Message.entity.Message.Status;
import com.marketplace.Message.exception.ExceptionRuntine;
import com.marketplace.Message.mapper.Messagemapper;
import com.marketplace.Message.repository.MessageRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MessageServiceImplement implements MessageService{
    private final Messagemapper messagemapper;
    private final MessageRepository messageRepository;
    private final UtilisateurService utilisateurService;
    @Override
    public void save(Long userId1,Long userId2, MessageDtoRequest messageDtoRequest) {
        if (messageDtoRequest==null)
             throw new ExceptionRuntine("pas de d'information");
        if(userId1 == userId2)
            throw new ExceptionRuntine("les utilisateur sont les memes");

        UtilisateurDtoProfile utilisateurDtoProfile = utilisateurService.getUserDatasById(userId1);
        UtilisateurDtoProfile utilisateurDtoProfile2 = utilisateurService.getUserDatasById(userId2);

        if(utilisateurDtoProfile == null || utilisateurDtoProfile2 == null )
            throw new ExceptionRuntine("Pas d'utilidateur");
        Message message = messagemapper.DtoToEntity(messageDtoRequest);
        message.setStatus(Status.SEND);
        message.setDate(LocalDateTime.now());
        message.setUserId1(userId1);
        message.setUserId2(userId2);
        messageRepository.save(message);
    }

    @Override
    public List<MessageDtoResponse> get(Long IdSend,Long IdReseive) {
        if (IdSend==null && IdReseive == null)
            throw new ExceptionRuntine("pas de d'information");
        
        List<MessageDtoResponse> messageDtoResponses = GetAll(IdSend);
        List<MessageDtoResponse> messages = new ArrayList<>();
        int i = 0;

        for (MessageDtoResponse messageDtoResponse : messageDtoResponses) {
            
            if(messageDtoResponse.userId1() == IdSend && messageDtoResponse.userId2() == IdReseive)
            {
                messages.add(i, messageDtoResponse);
                i++;
            }
            else if(messageDtoResponse.userId1() == IdReseive && messageDtoResponse.userId2() == IdSend)
            {
                messages.add(i, messageDtoResponse);
                i++;   
            }

        }

        return messages.stream().sorted(Comparator.comparing(MessageDtoResponse::Id)).collect(Collectors.toList());
        
    }

    @Override
    public List<MessageDtoResponse> GetAll(Long Id) {
        if(Id==null)
            throw new ExceptionRuntine("pas de d'information");
        List<MessageDtoResponse> message=messageRepository.findByUserId1(Id).stream().map(messagemapper::EntityToDto).collect(Collectors.toList());
        List<MessageDtoResponse> message1=messageRepository.findByUserId2(Id).stream().map(messagemapper::EntityToDto).collect(Collectors.toList());
        List<MessageDtoResponse> messagefinal =Stream.concat(message.stream(), message1.stream()).collect(Collectors.toList());
        
        if(messagefinal == null)
            throw new ExceptionRuntine("Pas de message");

        return messagefinal;
    }

    @Override
    public void delete(Long Id) {
        if (Id==null) 
            throw new ExceptionRuntine("pas de d'information");
        Message message = messageRepository.findById(Id).get();
        if (message==null) 
             throw new ExceptionRuntine("message n'existe pas");
        messageRepository.delete(message);
    }

}
