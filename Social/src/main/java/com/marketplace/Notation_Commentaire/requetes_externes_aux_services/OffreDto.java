package com.marketplace.Notation_Commentaire.requetes_externes_aux_services;

import java.time.LocalDateTime;

public record OffreDto(
    String name,
    Long offerId, 
    Long quantity, 
    Double price, 
    LocalDateTime date,
    String description,
    String image
) {

}
