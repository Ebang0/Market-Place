package com.marketplace.Notation_Commentaire.requetes_externes_aux_services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "UTILISATEUR")
public interface UserDatasClient {

}
