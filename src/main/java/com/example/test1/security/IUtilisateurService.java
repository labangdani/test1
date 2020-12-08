package com.example.test1.security;


import com.example.test1.modele.DTO.UtilisateurDto;
import com.example.test1.modele.Entity.Utilisateur;
import com.example.test1.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUtilisateurService implements UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

   /* @Override
    public Utilisateur save(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(utilisateurDto.getUsername());
        utilisateur.setMailU(utilisateurDto.getMailU());
        utilisateur.setPassword(utilisateurDto.getPassword());
        utilisateur.setTelU(utilisateurDto.getTelU());
        utilisateur.setAdresse(utilisateurDto.getAdresse());
        utilisateur.setSexe(utilisateurDto.getSexe());
        utilisateur.setRoles(utilisateurDto.getRoles());

        return utilisateurRepository.save(utilisateur);
    }*/

    @Override
    public Utilisateur rechercherUtilisateur(String email) {
        return utilisateurRepository.findByMailU(email);
    }

    @Override
    public Utilisateur findOne(Long idu) {
        return utilisateurRepository.findByIdU(idu);
    }

}
