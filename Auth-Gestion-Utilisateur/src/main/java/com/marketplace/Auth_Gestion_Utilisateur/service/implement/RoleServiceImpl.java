package com.marketplace.Auth_Gestion_Utilisateur.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoResponse;
import com.marketplace.Auth_Gestion_Utilisateur.entity.Role;
import com.marketplace.Auth_Gestion_Utilisateur.exception.ExceptionRuntine;
import com.marketplace.Auth_Gestion_Utilisateur.mappers.RoleMapper;
import com.marketplace.Auth_Gestion_Utilisateur.repository.RoleRepository;
import com.marketplace.Auth_Gestion_Utilisateur.service.RoleService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    @Override
    public void Save(RoleDtoRequest roleDtoRequest) {
        if(roleDtoRequest == null)
            throw new ExceptionRuntine("pas de donnée");
        
        roleRepository.save(roleMapper.DtoToEntity(roleDtoRequest));
    }

    @Override
    public RoleDtoResponse GetId(Long id) {
        if(id == null)
            throw new ExceptionRuntine("Manque d'information");

        Role role = roleRepository.findById(id).get();

        if(role == null)
            throw new ExceptionRuntine("N'existe pas");
        
        return roleMapper.EntityToDto(role);
    }

    @Override
    public List<RoleDtoResponse> GetAll() {
        List<Role> roles = roleRepository.findAll();

        if (roles == null) {
            throw new ExceptionRuntine("Pas de role");
        }

        return roles.stream().map(roleMapper::EntityToDto).collect(Collectors.toList());
    }

    @Override
    public void Delete(Long id) {
        if(id == null)
            throw new ExceptionRuntine("Manque d'information : id");
        
        Role role = roleRepository.findById(id).get();

        if(role == null)
            throw new ExceptionRuntine("Pas de role");
        
        roleRepository.delete(role);
    }

    @Override
    public void Update(Long id, RoleDtoRequest roleDtoRequest) {
        if(id == null || roleDtoRequest == null)
            throw new ExceptionRuntine("Manque d'information");

        Role role = roleRepository.findById(id).get();

        if(role == null)
            throw new ExceptionRuntine("Identifient non existant");
        
        Role role2 = roleMapper.DtoToEntity(roleDtoRequest);

        role2.setId(id);

        roleRepository.save(role2);
    }

}
