package com.example.test1.service;

import com.example.test1.modele.DTO.CommandeDto;
import com.example.test1.modele.Entity.Commande;

public interface CommandeService {
    public Commande save(CommandeDto commandeDto);
}
