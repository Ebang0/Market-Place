package com.marketplace.Auth_Gestion_Utilisateur.entity;

import com.marketplace.Auth_Gestion_Utilisateur.exception.ExceptionRuntine;

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
public class Utilisateur {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String tel;
    @Column(unique = true)
    private String login;
    @Column(nullable =  false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "idRole")
    private Role role;
    @Column(nullable =  true)
    private String Departement;
    @Column(nullable =  true)
    private String ville;
    @Column(nullable =  true)
    private boolean active;
    public Utilisateur orElseThrow(ExceptionRuntine exceptionRuntine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }
}
