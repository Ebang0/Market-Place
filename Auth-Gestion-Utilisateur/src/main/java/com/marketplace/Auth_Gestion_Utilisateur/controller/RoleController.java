package com.marketplace.Auth_Gestion_Utilisateur.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoResponse;
import com.marketplace.Auth_Gestion_Utilisateur.service.implement.RoleServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleServiceImpl roleServiceImpl;

    @GetMapping("/all")
    public List<RoleDtoResponse> GetAllRole()
    {
        return roleServiceImpl.GetAll();
    }

    @GetMapping("/{id}")
    public RoleDtoResponse Get(@PathVariable Long id) {
        return roleServiceImpl.GetId(id);
    }

    @PostMapping("/save")
    public void Save (@RequestBody RoleDtoRequest roleDtoRequest) {
        roleServiceImpl.Save(roleDtoRequest);
    }

    @PutMapping("/update/{id}")
    public void Update(@PathVariable Long id,@RequestBody RoleDtoRequest roleDtoRequest)
    {
        roleServiceImpl.Update(id,roleDtoRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable Long id)
    {
        roleServiceImpl.Delete(id);
    }
    
}
