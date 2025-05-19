package com.marketplace.Auth_Gestion_Utilisateur.mappers;

import org.springframework.stereotype.Service;

import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoResponse;
import com.marketplace.Auth_Gestion_Utilisateur.entity.Role;

@Service
public class RoleMapper {
    public Role DtoToEntity(RoleDtoRequest roleDtoRequest)
    {
        return new Role(null,roleDtoRequest.name());
    }

    public RoleDtoResponse EntityToDto(Role role)
    {
        return new RoleDtoResponse(role.getId(),role.getName());
    }
}
