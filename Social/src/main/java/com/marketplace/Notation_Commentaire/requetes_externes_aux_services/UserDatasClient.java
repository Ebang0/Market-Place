package com.marketplace.Notation_Commentaire.requetes_externes_aux_services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AUTH-GESTION-UTILISATEUR")
public interface UserDatasClient {
    @GetMapping("/api/auth_util/profil/{id}")
    UserDatas getUserDatasById(@PathVariable Long id);
}
