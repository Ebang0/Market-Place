
package com.marketplace.Message.CustomService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.marketplace.Message.dto.UtilisateurDtoProfile;

@FeignClient(name = "AUTH-GESTION-UTILISATEUR")
public interface UtilisateurService {

    @GetMapping("/api/auth_util/profile/{id}")
    UtilisateurDtoProfile getUserDatasById(@PathVariable Long id);
} 