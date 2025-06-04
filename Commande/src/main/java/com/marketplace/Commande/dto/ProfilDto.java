package com.marketplace.Commande.dto;

public record ProfilDto(
    String nom,
    String prenom,
    String email,
    String tel,
    String login,
    String departement,
    String ville
) {

}
