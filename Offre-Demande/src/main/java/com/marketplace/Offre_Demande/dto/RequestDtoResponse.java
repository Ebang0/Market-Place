package com.marketplace.Offre_Demande.dto;

import java.time.LocalDate;

public record RequestDtoResponse(String name,
                                Long id,
                                float quantity,
                                LocalDate date,
                                String description,
                                Double price) {

}
