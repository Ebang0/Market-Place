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
        
        if(roleRepository.findByName(roleDtoRequest.name().toUpperCase()) != null)
            throw new ExceptionRuntine("Role existant");
        
        RoleDtoRequest roleDtoRequest2 = new RoleDtoRequest(roleDtoRequest.name().toUpperCase());
        roleRepository.save(roleMapper.DtoToEntity(roleDtoRequest2));
    }

    @Override
    public RoleDtoResponse GetId(Long id) {
        if(id == null)
            throw new ExceptionRuntine("Manque d'information");

        Role role = roleRepository.findById(id).orElseThrow(() -> new ExceptionRuntine("N'existe pas"));
        
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
        
        Role role = roleRepository.findById(id).orElseThrow(() -> new ExceptionRuntine("Pas de role"));
        
        roleRepository.delete(role);
    }

    @Override
    public void Update(Long id, RoleDtoRequest roleDtoRequest) {
        if(id == null || roleDtoRequest == null)
            throw new ExceptionRuntine("Manque d'information");

        Role role = roleRepository.findById(id).orElseThrow(() -> new ExceptionRuntine("Pas de role"));

        Role role2 = roleMapper.DtoToEntity(roleDtoRequest);

        if(roleRepository.findByName(roleDtoRequest.name().toUpperCase()) != null)
            throw new ExceptionRuntine("Role existant");

        role2.setId(id);
        role2.setName(role.getName().toUpperCase());

        roleRepository.save(role2);
    }

}
