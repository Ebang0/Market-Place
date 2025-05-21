package com.marketplace.Auth_Gestion_Utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.Auth_Gestion_Utilisateur.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByName(String name);
}
