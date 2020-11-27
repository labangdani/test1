package com.example.test1.security.service;

import com.example.test1.modele.DTO.CommandeDto;
import com.example.test1.modele.Entity.Commande;

import java.util.List;

public interface CommandeService {
    public Commande save(CommandeDto commandeDto);
    public List<Commande> listCommande(String nomR);
}
