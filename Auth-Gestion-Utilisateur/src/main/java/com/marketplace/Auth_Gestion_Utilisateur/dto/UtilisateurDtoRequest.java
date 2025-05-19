package com.marketplace.Auth_Gestion_Utilisateur.dto;

public record UtilisateurDtoRequest(
    String nom,
    String prenom,
    String email,
    String tel,
    String login,
    String password,
    Long idRole,
    String departement,
    String ville,
    Double longitude,
    Double latitude) {    
}
