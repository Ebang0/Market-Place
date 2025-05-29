package com.marketplace.Message.controleur;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MessageDtoResponse>>  getalluser(@PathVariable Long Id)
    {
        return ResponseEntity.ok(messageServiceImplement.GetAll(Id));

    }
    
    @PostMapping("/{userId1}/save/{userId2}")
    public ResponseEntity<?>Save(@RequestBody MessageDtoRequest messageDtoRequest,@PathVariable Long userId1,@PathVariable Long userId2){
        messageServiceImplement.save(userId1,userId2,messageDtoRequest);

        return ResponseEntity.ok().body("Envoyer");
    }

    @GetMapping("/all/{idSend}/reseive/{idReseive}")
    public ResponseEntity<List<MessageDtoResponse>>  getAllReseive(@PathVariable Long idSend,@PathVariable Long idReseive)
    {
        return ResponseEntity.ok(messageServiceImplement.get(idSend, idReseive)); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>Delete(@PathVariable Long id)
    {
        messageServiceImplement.delete(id);
        return ResponseEntity.ok().body("Supprimer");
    }
    
}
    

