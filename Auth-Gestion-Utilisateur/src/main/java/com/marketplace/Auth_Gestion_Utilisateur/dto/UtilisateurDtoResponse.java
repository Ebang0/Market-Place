package com.marketplace.Auth_Gestion_Utilisateur.dto;

public record UtilisateurDtoResponse(
    Long id,
    String nom,
    String prenom,
    String email,
    boolean active) {

}
