package com.marketplace.Message.controleur;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.Message.dto.MessageDtoRequest;
import com.marketplace.Message.dto.MessageDtoResponse;
import com.marketplace.Message.service.MessageServiceImplement;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageServiceImplement messageServiceImplement;
        
    @GetMapping("/all/{Id}")
    public List<MessageDtoResponse> getalluser(@PathVariable Long Id)
    {
        return messageServiceImplement.GetAll(Id);

    }
    
    @PostMapping("/save")
    public void Save(@RequestBody MessageDtoRequest messageDtoRequest){
        messageServiceImplement.save(messageDtoRequest);
    }

    @GetMapping("/all/{idSend}/reseive/{idReseive}")
    public List<MessageDtoResponse> getAllReseive(@PathVariable Long idSend,@PathVariable Long idReseive)
    {
        return messageServiceImplement.get(idSend, idReseive);
    }

    @DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable Long id)
    {
        messageServiceImplement.delete(id);
    }
    
}
    

