package com.marketplace.Offre_Demande.controller;

import com.marketplace.Offre_Demande.dto.OfferDtoRequest;
import com.marketplace.Offre_Demande.dto.OfferDtoResponse;
import com.marketplace.Offre_Demande.service.OfferService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;
    //faire une offre
    @PostMapping("/save/{producerId}")
    public ResponseEntity<?> createOffer(@PathVariable Long producerId, @RequestBody OfferDtoRequest dto) {
        offerService.createOffer(producerId, dto);
        return ResponseEntity.ok().body("Offre engistrer");
    }
    //detail offre
    @GetMapping("/get/{id}")
    public ResponseEntity<OfferDtoResponse> getOffer(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getOffer(id));
    }
    //mise ajout quantite
    @PutMapping("{idOffer}/update/{quantite}")
    public ResponseEntity<?> updateOffer(@PathVariable Long idOffer, @PathVariable Long quantite) {
        offerService.updateOfferQuantity(idOffer, quantite);
        return ResponseEntity.ok().body("Mise a jour effectuer");
    }
    //supprimer l'offre
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.ok().body("Offre supprimer");
    }
    //valider l'offre
    @PatchMapping("/{id}/validate")
    public ResponseEntity<?> validateOffer(@PathVariable Long id) {
        offerService.validateOffer(id);
        return ResponseEntity.ok().body("Offre Autoriser");
    }
    //annuler l'offre
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOffer(@PathVariable Long id) {
        offerService.cancelOffer(id);
        return ResponseEntity.ok().body("Offre annuler");
    }
    //par produit
    @GetMapping("/producer/{productId}")
    public ResponseEntity<List<OfferDtoResponse>> getOffersByProducer(@PathVariable Long productId) {
        return ResponseEntity.ok(offerService.getOffersByProduct(productId));
    }

    @GetMapping("/ville")
    public ResponseEntity<List<OfferDtoResponse>> getOfferByVille(@PathParam("ville")String ville)
    {
        return ResponseEntity.ok(offerService.getOfferByVille(ville));
    }
}
