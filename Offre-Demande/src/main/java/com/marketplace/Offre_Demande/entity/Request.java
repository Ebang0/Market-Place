package com.marketplace.Offre_Demande.entity;

import java.time.LocalDate;

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
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long idConsommateur;
    @Column(nullable = true)
    private Long idProducteur;
    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;
    private float quantity;
    private String description;
    private Double price;
    private Boolean validate;
    private Status status;
    private LocalDate date;
    private LocalDate dateCreate;
    
    public enum Status{
        Reading,
        Validate,
        End
    }
}
