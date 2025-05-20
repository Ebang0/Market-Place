package com.marketplace.Auth_Gestion_Utilisateur.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.Auth_Gestion_Utilisateur.dto.LoginDto;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoResponse;
import com.marketplace.Auth_Gestion_Utilisateur.exception.ExceptionRuntine;
import com.marketplace.Auth_Gestion_Utilisateur.service.implement.UtilisateurServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth_util")
public class UtilisateurController {
    
    private final UtilisateurServiceImpl utilisateurServiceImpl;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/connexion")
    public ResponseEntity<?> Login(@RequestBody LoginDto loginDto)
    {
        try
        {
            authenticationManager .authenticate(new UsernamePasswordAuthenticationToken(loginDto.login(),loginDto.password()));
            return ResponseEntity.ok().body("Login succes");
        }
        catch(Exception ex)
        {
            throw new ExceptionRuntine("Login echec");
        }
    }

    @PostMapping("/inscription")
    public ResponseEntity<?> Inscription(@RequestBody UtilisateurDtoRequest utilisateurDtoRequest)
    {
        utilisateurServiceImpl.Inscription(utilisateurDtoRequest);
        return ResponseEntity.ok().body("Inscription Terminer");
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> GetProfile(@PathVariable Long id)
    {
        return ResponseEntity.ok(utilisateurServiceImpl.Get(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<UtilisateurDtoResponse>>GetActive()
    {
        return ResponseEntity.ok(utilisateurServiceImpl.GetAllActive());
    }

    @GetMapping("/role/id")
    public ResponseEntity<List<UtilisateurDtoResponse>> GetAllForRole(@PathVariable Long id)
    {
        return ResponseEntity.ok(utilisateurServiceImpl.GetAllUtilisateurRole(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id)
    {
        utilisateurServiceImpl.Delete(id);
        return ResponseEntity.ok().body("Utilisateur supprimer");
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<?> ActiveUser(@PathVariable Long id)
    {
        utilisateurServiceImpl.ActiveUtilisateur(id);
        return ResponseEntity.ok().body("Utilisateur active");
    }


}
