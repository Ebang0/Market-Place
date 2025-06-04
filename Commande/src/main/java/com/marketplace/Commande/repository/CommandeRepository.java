package com.marketplace.Commande.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.Commande.entity.Commande;
import java.util.List;


public interface CommandeRepository  extends JpaRepository<Commande, Long>{
    List<Commande> findByIdUser(Long idUser);
}
