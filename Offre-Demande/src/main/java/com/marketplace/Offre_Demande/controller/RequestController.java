package com.marketplace.Offre_Demande.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.Offre_Demande.dto.RequestDtoRequest;
import com.marketplace.Offre_Demande.dto.RequestDtoResponse;
import com.marketplace.Offre_Demande.service.RequestServiceImplement;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestServiceImplement requestServiceImplement;

    @CrossOrigin
    @PostMapping("/save/{id}")
    public ResponseEntity<?> save(@RequestBody RequestDtoRequest request, @PathVariable Long id)
    {
        requestServiceImplement.Save(id, request);

        return ResponseEntity.ok().body("Envoyer");
    }

    @CrossOrigin
    @PutMapping("/validate/{id}")
    public ResponseEntity<?> ValidateRequest(@PathVariable Long id)
    {
        requestServiceImplement.UpdateValidation(id);
        return ResponseEntity.ok().body("Demande valide");
    }

    @CrossOrigin
    @PutMapping("/{idProducteur}/accept/{idRequest}")
    public ResponseEntity<?> AcceptRequest(@PathVariable Long idRequest,@PathVariable Long idProducteur)
    {
        requestServiceImplement.ValidateRequestProducteur(idProducteur, idRequest);
        return ResponseEntity.ok().body("Demande accepter");
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateRequest(@PathVariable Long id,@RequestBody RequestDtoRequest request)
    {
        requestServiceImplement.Update(id, request);
        return ResponseEntity.ok().body("Demande mise a jour");
    }
    
    @CrossOrigin
    @PutMapping("/annuler/{id}")
    public ResponseEntity<?> AnnulerRequest(@PathVariable Long id)
    {
        requestServiceImplement.AnnulerRequest(id);
        return ResponseEntity.ok().body("Demande Annuler");
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public ResponseEntity<RequestDtoResponse> get(@PathVariable Long id)
    {
        return ResponseEntity.ok(requestServiceImplement.getRequest(id));
    }

    @CrossOrigin
    @GetMapping("/get/all")
    public ResponseEntity<List<RequestDtoResponse>> getAll()
    {
        return ResponseEntity.ok(requestServiceImplement.getAll());
    }

    @CrossOrigin
    @GetMapping("/get/product/id")
    public ResponseEntity<List<RequestDtoResponse>> getOfProduct(@PathVariable Long id)
    {
        return ResponseEntity.ok(requestServiceImplement.getAllForProduct(id)) ;
    }

    @CrossOrigin
    @GetMapping("/get/validte/{id}")
    public ResponseEntity<List<RequestDtoResponse>> getValidate(@PathVariable Long id)
    {
        return ResponseEntity.ok(requestServiceImplement.getAllForValidate(id));
    }
}
