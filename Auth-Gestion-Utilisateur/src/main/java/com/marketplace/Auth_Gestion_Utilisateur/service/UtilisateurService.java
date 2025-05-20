package com.marketplace.Auth_Gestion_Utilisateur.service;

import java.util.List;

import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoProfile;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoResponse;

public interface UtilisateurService {
    public void Inscription(UtilisateurDtoRequest utilisateurDtoRequest);
    public void Update(Long id,UtilisateurDtoRequest utilisateurDtoRequest);
    public void ActiveUtilisateur(Long id);
    public void Delete(Long id);
    public UtilisateurDtoProfile Get(Long id);
    public List<UtilisateurDtoResponse> GetAllActive();
    public List<UtilisateurDtoResponse> GetAllUtilisateurRole(Long id);
    public List<UtilisateurDtoResponse> GetAllVille(String ville);
    public List<UtilisateurDtoResponse> Seach(String name);    
}
