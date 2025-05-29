package com.marketplace.Offre_Demande.dto;

import java.time.LocalDateTime;

public record OfferDtoResponse( String name,
                                Long offerId, 
                                Long quantity, 
                                Double price, 
                                String ville,
                                LocalDateTime date,
                                String description,
                                String image) {

}
