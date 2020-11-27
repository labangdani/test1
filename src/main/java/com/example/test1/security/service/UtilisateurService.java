package com.example.test1.security.service;

import com.example.test1.modele.DTO.UtilisateurDto;
import com.example.test1.modele.Entity.Utilisateur;

public interface UtilisateurService {

    public Utilisateur save(UtilisateurDto utilisateurDto);
    public Utilisateur rechercherUtilisateur(String mailU);

}
