package com.marketplace.Auth_Gestion_Utilisateur.controller;

import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.RoleDtoResponse;
import com.marketplace.Auth_Gestion_Utilisateur.service.RoleService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<RoleDtoResponse>> getAllRoles() {
        List<RoleDtoResponse> roles = roleService.GetAll();
        return ResponseEntity.ok(roles);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        RoleDtoResponse role = roleService.GetId(id);
        return ResponseEntity.ok(role);
    }

    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<?> createRole(@RequestBody RoleDtoRequest roleDtoRequest) {
        roleService.Save(roleDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role entre enregistrer");
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRole(
            @PathVariable Long id,
            @RequestBody RoleDtoRequest roleDtoRequest) {
        roleService.Update(id, roleDtoRequest);
        return ResponseEntity.ok().body("Role mise ajout");
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleService.Delete(id);
        return ResponseEntity.ok().body("Role supprimer");
    }
}