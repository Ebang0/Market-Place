package com.marketplace.Commande.customService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.marketplace.Commande.dto.OffreDto;
import com.marketplace.Commande.dto.ProduitDto;

@FeignClient(name = "OFFRE-PRODUIT")
public interface OffreService {
    @GetMapping("/api/offer/get/{id}")
    OffreDto getOffre(@PathVariable Long id);

    @PutMapping("/api/offer/{idOffer}/update/{quantite}")
    void updateOfferQuantity(@PathVariable Long idOffer, @PathVariable Double quantite);


    @GetMapping("/api/product/get/{id}")
    public ProduitDto getProduct(@PathVariable Long id);
}
