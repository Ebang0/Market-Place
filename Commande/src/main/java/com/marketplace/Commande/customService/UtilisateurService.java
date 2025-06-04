package com.marketplace.Commande.customService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.marketplace.Commande.dto.ProfilDto;

@FeignClient(name ="AUTH-GESTION-UTILISATEUR")
public interface UtilisateurService {

    @GetMapping("/api/auth_util/profil/{id}")
    ProfilDto getProfil(@PathVariable Long id);
    
}
