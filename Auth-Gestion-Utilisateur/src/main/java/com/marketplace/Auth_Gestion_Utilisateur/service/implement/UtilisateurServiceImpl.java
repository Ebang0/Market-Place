package com.marketplace.Auth_Gestion_Utilisateur.service.implement;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoProfile;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoRequest;
import com.marketplace.Auth_Gestion_Utilisateur.dto.UtilisateurDtoResponse;
import com.marketplace.Auth_Gestion_Utilisateur.entity.Role;
import com.marketplace.Auth_Gestion_Utilisateur.entity.Utilisateur;
import com.marketplace.Auth_Gestion_Utilisateur.exception.ExceptionRuntine;
import com.marketplace.Auth_Gestion_Utilisateur.mappers.UtilisateurMapper;
import com.marketplace.Auth_Gestion_Utilisateur.repository.RoleRepository;
import com.marketplace.Auth_Gestion_Utilisateur.repository.UtilisateurRepository;
import com.marketplace.Auth_Gestion_Utilisateur.service.UtilisateurService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService{
   
    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final RoleRepository roleRepository;

    @Override
    public void Inscription(UtilisateurDtoRequest utilisateurDtoRequest) {
        if(utilisateurDtoRequest == null)
        {
            throw new ExceptionRuntine("Pas d'information");
        }

        if(utilisateurRepository.findByLogin(utilisateurDtoRequest.username()) != null)
            throw new ExceptionRuntine("Login existant");

        if(utilisateurRepository.findByTel(utilisateurDtoRequest.contact()) != null)
            throw new ExceptionRuntine("contact existant");
        
        if(utilisateurRepository.findByEmail(utilisateurDtoRequest.email()) != null)
            throw new ExceptionRuntine("Email existant");
        
        boolean active = false;

        Role role = roleRepository.findById(utilisateurDtoRequest.idRole()).orElseThrow(() -> new ExceptionRuntine("Entrer un login"));

        if(role.getName().equals("PRODUCTEUR"))
            active = false;
        else
            active = true;

        Utilisateur utilisateur = new Utilisateur(null,
                                    utilisateurDtoRequest.nom(),
                                    utilisateurDtoRequest.prenom(),
                                    utilisateurDtoRequest.email(),
                                    utilisateurDtoRequest.contact(),
                                    utilisateurDtoRequest.username(), 
                                    passwordEncoder.encode(utilisateurDtoRequest.password()),
                                    role,
                                    utilisateurDtoRequest.departement(),
                                    utilisateurDtoRequest.ville(),
                                    active);

        
        utilisateurRepository.save(utilisateur);
    }


    @Override
    public void Update(Long id, UtilisateurDtoRequest utilisateurDtoRequest) {
        if(id == null)
            throw new ExceptionRuntine("Pas d'information");

        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new ExceptionRuntine("Utilisateur ayant l'identifiant :" + id + " n'existe pas"));

        Utilisateur utilisateur2 = utilisateurMapper.DtotoEntity(utilisateurDtoRequest);
        utilisateur2.setId(utilisateur.getId());
    }

    @Override
    public void ActiveUtilisateur(Long id) {
        if(id == null)
            throw new ExceptionRuntine("Pas d'information");

        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new ExceptionRuntine("Utilisateur ayant l'identifiant :" + id + " n'existe pas"));

        utilisateur.setActive(true);

        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void Delete(Long id) {
        if(id == null)
            throw new ExceptionRuntine("Pas d'information");

        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new ExceptionRuntine("Utilisateur ayant l'identifiant :" + id + " n'existe pas"));

        utilisateurRepository.delete(utilisateur);
    }

    @Override
    public UtilisateurDtoProfile Get(Long id) {
        if(id == null)
            throw new ExceptionRuntine("Pas d'information");

        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new ExceptionRuntine("Utilisateur de l'identifiant" + id + " n'existe pas"));

        return utilisateurMapper.EntityToDtoProfile(utilisateur);
    }

    @Override
    public List<UtilisateurDtoResponse> GetAllActive() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findByActive(false);
        if(utilisateurs.isEmpty())
            throw new ExceptionRuntine("Pas d'utilisateur desactiver");
        
        return utilisateurs.stream().map(utilisateurMapper::EntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDtoResponse> GetAllUtilisateurRole(Long id) {
        if(id == null)
            throw new ExceptionRuntine("pas information");
        Role role = roleRepository.findById(id).orElseThrow(() -> new ExceptionRuntine("role n'existe pas"));
        
        List<Utilisateur> utilisateurs = utilisateurRepository.findByRole(role);
        
        if(utilisateurs == null || utilisateurs.size() == 0)
            throw new ExceptionRuntine("Pas d'utilisateur ayant le " + role.getName());
        
        return utilisateurs.stream().map(utilisateurMapper::EntityToDto).collect(Collectors.toList());
    }


    @Override
    public List<UtilisateurDtoResponse> GetAllVille(String ville) {
        if(ville == null)
            throw new ExceptionRuntine("pas d'information sur la ville");

        List<Utilisateur> utilisateurs = utilisateurRepository.findByVille(ville);

        if(utilisateurs == null)
            throw new ExceptionRuntine("Aucun utilisateur de cet ville existe");
        
            return utilisateurs.stream().map(utilisateurMapper::EntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDtoResponse> Seach(String name) {
        if(name == null)
            throw new ExceptionRuntine("pas information sur l'utilisateur");

        List<Utilisateur> utilisateur = utilisateurRepository.findByNom(name);

        if(utilisateur == null || utilisateur.size() == 0)
            throw new ExceptionRuntine("utilisateur n'existe pas");
        
        return utilisateur.stream().map(utilisateurMapper::EntityToDto).collect(Collectors.toList());
    }
}
