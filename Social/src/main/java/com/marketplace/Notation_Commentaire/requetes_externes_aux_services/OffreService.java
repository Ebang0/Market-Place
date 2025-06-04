package com.marketplace.Notation_Commentaire.requetes_externes_aux_services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "OFFRE-PRODUIT")
public interface OffreService {
    @GetMapping("/api/offer/get/{id}")
    OffreDto getOffre(@PathVariable Long id);
}
