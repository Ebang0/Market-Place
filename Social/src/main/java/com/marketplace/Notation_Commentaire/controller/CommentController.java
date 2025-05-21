package com.marketplace.Notation_Commentaire.controller;

// Ce fichier gère les demandes HTTP 

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.marketplace.Notation_Commentaire.dto.CommentDtoRequest;
import com.marketplace.Notation_Commentaire.dto.CommentDtoResponse;
import com.marketplace.Notation_Commentaire.service.CommentServiceImplement;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImplement commentServiveImplement;

    @GetMapping("/getComment/{idOffre}")
    public List<CommentDtoResponse> getComment(@PathVariable Long idOffre){
        return commentServiveImplement.getAll(idOffre);
    }

    // POST /comments → Création
    @PostMapping("/{idUser}/postComment/{idOffre}")
    
    public ResponseEntity<?> postComment(@PathVariable Long idUser, @PathVariable Long idOffre, @RequestBody CommentDtoRequest commentDtoRequest){
              
        
        boolean val = commentServiveImplement.Save(idUser, idOffre, commentDtoRequest);

        if(val == false)
            return ResponseEntity.status(HttpStatus.FOUND).body("Pas de commentaires");
        else 
            return ResponseEntity.ok().body("Commentaire enregistré");

    }
 

}



