package com.marketplace.Offre_Demande.dto;

import java.time.LocalDate;

public record RequestDtoRequest(String name,Long idProduct,
                                float quantity,
                                LocalDate date,
                                String description){

}
