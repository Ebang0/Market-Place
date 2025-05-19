package com.marketplace.Auth_Gestion_Utilisateur.service;

import java.util.List;

import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoResponse;

public interface RoleService {
    public void Save(RoleDtoRequest roleDtoRequest);
    public RoleDtoResponse GetId(Long id);
    public List<RoleDtoResponse> GetAll();
    public void Delete(Long id);
    public void Update(Long id,RoleDtoRequest roleDtoRequest);
}
