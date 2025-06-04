package com.marketplace.Auth_Gestion_Utilisateur.dto;

public record UtilisateurDtoRequest(
    String nom,
    String prenom,
    String email,
    String contact,
    String username,
    String password,
    Long idRole,
    String departement,
    String ville) {    
}
