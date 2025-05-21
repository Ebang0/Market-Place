package com.marketplace.Commande.dto;

import java.time.LocalDateTime;

public record CommandeDtoResponse(Long id, LocalDateTime commandeDate, String statut, Double proxitotal) {

}
