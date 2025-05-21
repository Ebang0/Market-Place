package com.marketplace.Commande.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.Commande.entity.Commande;

public interface CommandeRepository  extends JpaRepository<Commande, Long>{

}
