package com.marketplace.Auth_Gestion_Utilisateur.service;

import java.util.List;

import com.marketplace.Auth_Gestion_Utilisateur.dto.LoginDto;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoProfile;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoRespnose;

public interface UtilisateurService {
    public void Inscription(UtilisateurDtoRequest utilisateurDtoRequest);
    public void Connexion(LoginDto loginDto);
    public void Update(Long id,UtilisateurDtoRequest utilisateurDtoRequest);
    public void ActiveUtilisateur(Long id);
    public void Delete(Long id);
    public UtilisateurDtoProfile Get(Long id);
    public List<UtilisateurDtoRespnose> GetAllActive();
    public List<UtilisateurDtoRespnose> GetAllUtilisateurRole(Long id);
    public List<UtilisateurDtoRespnose> GetAllVille(String ville);
    public List<UtilisateurDtoRespnose> Seach(String name);    
}
