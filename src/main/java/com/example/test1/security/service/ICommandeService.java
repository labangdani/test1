package com.example.test1.security.service;


import com.example.test1.modele.DTO.CommandeDto;
import com.example.test1.modele.Entity.Commande;
import com.example.test1.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICommandeService implements CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public Commande save(CommandeDto commandeDto) {
        Commande commande = new Commande();
        commande.setIdcom(commandeDto.getIdcom());
        commande.setNbrplat(commandeDto.getNbrplat());
        commande.setTotal(commandeDto.getTotal());
        commande.setFraislivraison(commandeDto.getFraislivraison());
        commande.setSousmontant(commandeDto.getSousmontant());
        commande.setDate(commandeDto.getDate());
        commande.setRestaurant(commandeDto.getRestaurant());
        commande.setUser(commandeDto.getUser());
        return commandeRepository.save(commande);
    }

    @Override
    public List<Commande> listCommande(String nomR) {

        return commandeRepository.findAllByRestaurant(nomR);
    }
}
