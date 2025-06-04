package com.marketplace.Auth_Gestion_Utilisateur.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.Auth_Gestion_Utilisateur.dto.LoginDto;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoProfile;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoResponse;
import com.marketplace.Auth_Gestion_Utilisateur.exception.ExceptionRuntine;
import com.marketplace.Auth_Gestion_Utilisateur.security.JwtService;
import com.marketplace.Auth_Gestion_Utilisateur.service.implement.CustomServic;
import com.marketplace.Auth_Gestion_Utilisateur.service.implement.UtilisateurServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth_util")
public class UtilisateurController {
    
    private final UtilisateurServiceImpl utilisateurServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final CustomServic customServic;
    private final JwtService jwtService;

    @CrossOrigin
    @PostMapping("/connexion")
    public ResponseEntity<?> Login(@RequestBody LoginDto loginDto)
    {
        try
        {
            Authentication authentication = authenticationManager .authenticate(new UsernamePasswordAuthenticationToken(loginDto.login(),loginDto.password()));
            if(authentication.isAuthenticated())
            {
                Map<String,Object>  authData = new HashMap<>();

                authData.put("token", jwtService.generateToken(loginDto.login()));
                authData.put("type", "Bearer");
                return ResponseEntity.ok(authData);
            }
            throw new ExceptionRuntine("Login echec");
        }
        catch(Exception ex)
        {
            throw new ExceptionRuntine("Login echec");
        }
    }

    @CrossOrigin
    @PostMapping("/inscription")
    public ResponseEntity<?> Inscription(@RequestBody UtilisateurDtoRequest utilisateurDtoRequest)
    {
        utilisateurServiceImpl.Inscription(utilisateurDtoRequest);
        return ResponseEntity.ok().body("Inscription Terminer");
    }

    @CrossOrigin
    @GetMapping("/profil/{id}")
    public ResponseEntity<?> GetProfile(@PathVariable Long id)
    {
        return ResponseEntity.ok(utilisateurServiceImpl.Get(id));
    }

    @CrossOrigin
    @GetMapping("/desactive")
    public ResponseEntity<List<UtilisateurDtoResponse>>GetDesActive()
    {
        return ResponseEntity.ok(utilisateurServiceImpl.GetAllActive());
    }

    @CrossOrigin
    @GetMapping("/role/{id}")
    public ResponseEntity<List<UtilisateurDtoResponse>> GetAllForRole(@PathVariable Long id)
    {
        return ResponseEntity.ok(utilisateurServiceImpl.GetAllUtilisateurRole(id));
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id)
    {
        utilisateurServiceImpl.Delete(id);
        return ResponseEntity.ok().body("Utilisateur supprimer");
    }

    @CrossOrigin
    @PutMapping("/active/{id}")
    public ResponseEntity<?> ActiveUser(@PathVariable Long id)
    {
        utilisateurServiceImpl.ActiveUtilisateur(id);
        return ResponseEntity.ok().body("Utilisateur active");
    }

    @CrossOrigin
    @GetMapping("/token/{id}")
    public String ValidateToken(@RequestParam("token") String token, @PathVariable Long id)
    {
        UtilisateurDtoProfile utilisateurDtoProfile = utilisateurServiceImpl.Get(id);
        if(jwtService.validateToken(token,customServic.loadUserByUsername(utilisateurDtoProfile.login())))
        {
            return "valide";
        }
        else
            return "Non valide";
    }
}
