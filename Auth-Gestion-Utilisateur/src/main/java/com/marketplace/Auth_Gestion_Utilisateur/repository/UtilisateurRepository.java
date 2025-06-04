package com.marketplace.Auth_Gestion_Utilisateur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.Auth_Gestion_Utilisateur.entity.Role;
import com.marketplace.Auth_Gestion_Utilisateur.entity.Utilisateur;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur,Long>{
    List<Utilisateur> findByRole(Role role);
    List<Utilisateur> findByActive(boolean active);
    List<Utilisateur> findByVille(String ville);
    Utilisateur  findByLogin(String login);
    Utilisateur findByTel(String tel);
    Utilisateur findByEmail(String email);
    List<Utilisateur> findByNom(String nom);
    List<Utilisateur> findByPrenom(String prenom);
}
