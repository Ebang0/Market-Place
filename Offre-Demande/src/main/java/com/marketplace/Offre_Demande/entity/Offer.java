package com.marketplace.Offre_Demande.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;
    @ManyToOne
    @JoinColumn(name="producerId")
    private Product product;
    private Long idProducteur;
    @Column(nullable = false)
    private Long quantity;
    private Double price;
    private String description;
    private LocalDateTime date;
    private String image;
    private boolean validate;
}