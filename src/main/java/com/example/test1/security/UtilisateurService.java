package com.example.test1.security;

import com.example.test1.modele.DTO.UtilisateurDto;
import com.example.test1.modele.Entity.Utilisateur;

public interface UtilisateurService {

    //public Utilisateur save(UtilisateurDto utilisateurDto);
    public Utilisateur rechercherUtilisateur(String mailU);
    public Utilisateur findOne(Long idu);
}
