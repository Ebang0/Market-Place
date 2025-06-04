package com.marketplace.Commande.dto;

import java.time.LocalDateTime;

import com.marketplace.Commande.entity.Commande.Status;

public record CommandeDtoResponse(Long id, LocalDateTime commandeDate, Status statut,Double quantite, Double proxitotal) {

}
