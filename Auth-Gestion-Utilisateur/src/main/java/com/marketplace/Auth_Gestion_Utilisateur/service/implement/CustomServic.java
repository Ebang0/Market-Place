package com.marketplace.Auth_Gestion_Utilisateur.service.implement;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marketplace.Auth_Gestion_Utilisateur.entity.Role;
import com.marketplace.Auth_Gestion_Utilisateur.entity.Utilisateur;
import com.marketplace.Auth_Gestion_Utilisateur.repository.UtilisateurRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomServic implements UserDetailsService{
    private final UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur util = utilisateurRepository.findByLogin(username);
        
        if (util == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le login: " + username);
        }
        
        if (!util.isActive())
        {
            throw new UsernameNotFoundException("Utilisateur non activé");
        }

        List<GrantedAuthority> authorities = buildAuthorities(util.getRole());
        
        return new User(
            util.getLogin(),
            util.getPassword(),
            authorities
        );
    }

    private List<GrantedAuthority> buildAuthorities(Role role) {
    // Format standard Spring Security pour les rôles
        String roleName = role.getName().startsWith("ROLE_") 
            ? role.getName() 
            : "ROLE_" + role.getName();

        return Collections.singletonList(new SimpleGrantedAuthority(roleName));
    }
     
}
