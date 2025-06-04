package com.marketplace.Commande.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.Commande.dto.CommandeDtoRequest;
import com.marketplace.Commande.dto.CommandeDtoResponse;
import com.marketplace.Commande.service.CommandeService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/commande")
@RequiredArgsConstructor
public class CommandeController {
    private final CommandeService commandeService;

    @CrossOrigin
    @PostMapping("/{idUser}/save/{idOffer}")
    public ResponseEntity<?> save(@RequestBody CommandeDtoRequest commandeDtoRequest,@PathVariable Long idUser,@PathVariable Long idOffer){
        commandeService.Save(idOffer, idUser, commandeDtoRequest);

        return ResponseEntity.ok().body("Commande passer");
    }

    @CrossOrigin
    @GetMapping("/all/{idUser}")
    public ResponseEntity< List<CommandeDtoResponse> >getAllForUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(commandeService.getAllForUser(idUser));
    }

    @CrossOrigin
    @DeleteMapping("/annuler/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        commandeService.Annuler(id);

        return ResponseEntity.ok().body("Commmande Annuler");
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<CommandeDtoResponse>> getAll() {
        return ResponseEntity.ok(commandeService.getAll());
    }
    
}
