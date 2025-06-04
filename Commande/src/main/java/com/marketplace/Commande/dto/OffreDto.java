package com.marketplace.Commande.dto;

import java.time.LocalDateTime;

public record OffreDto(
        String name,
        Long offerId, 
        Long quantity, 
        Double price, 
        String ville,
        LocalDateTime date,
        String description,
        String image
) {

}
