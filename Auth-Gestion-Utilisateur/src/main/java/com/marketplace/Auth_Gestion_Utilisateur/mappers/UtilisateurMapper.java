package com.marketplace.Auth_Gestion_Utilisateur.mappers;

import org.springframework.stereotype.Service;

import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoProfile;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoResponse;
import com.marketplace.Auth_Gestion_Utilisateur.entity.Role;
import com.marketplace.Auth_Gestion_Utilisateur.entity.Utilisateur;


@Service
public class UtilisateurMapper {
    
    public Utilisateur DtotoEntity(UtilisateurDtoRequest utilisateurDtoRequest)
    {
        Role role = new Role();
        role.setId(utilisateurDtoRequest.idRole());
        return new Utilisateur(null,
                                utilisateurDtoRequest.nom(),
                                utilisateurDtoRequest.prenom(),
                                utilisateurDtoRequest.email(),
                                utilisateurDtoRequest.tel(),
                                utilisateurDtoRequest.login(),
                                utilisateurDtoRequest.password(),
                                role,
                                utilisateurDtoRequest.departement(),
                                utilisateurDtoRequest.ville(),
                                false);
    }

    public UtilisateurDtoResponse EntityToDto(Utilisateur utilisateur)
    {
        return new UtilisateurDtoResponse(utilisateur.getId(),
                                          utilisateur.getNom(),
                                          utilisateur.getPrenom(),
                                          utilisateur.getEmail(),
                                          utilisateur.isActive());
    }

    public UtilisateurDtoProfile EntityToDtoProfile(Utilisateur utilisateur)
    {
        return new UtilisateurDtoProfile(
                                            utilisateur.getNom(),
                                            utilisateur.getPrenom(),
                                            utilisateur.getEmail(),
                                            utilisateur.getTel(),
                                            utilisateur.getLogin(),
                                            utilisateur.getDepartement(),
                                            utilisateur.getVille());
    }
}
