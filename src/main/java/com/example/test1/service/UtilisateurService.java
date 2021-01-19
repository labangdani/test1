package com.example.test1.service;

import com.example.test1.modele.Entity.Utilisateur;

public interface UtilisateurService {

    //public Utilisateur save(UtilisateurDto utilisateurDto);
    public Utilisateur rechercherUtilisateur(String mailU);
    public Utilisateur findOne(Long idu);
}
