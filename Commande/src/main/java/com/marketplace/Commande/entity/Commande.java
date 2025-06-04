package com.marketplace.Commande.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idOffer;
    private Long idUser;
    private Double quantite;
    private LocalDateTime commandeDate;
    private Status statut;
    private Double prixtotal;

    public enum Status
    {
        LOADING
    } 
}
