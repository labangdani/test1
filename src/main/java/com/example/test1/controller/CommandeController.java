package com.example.test1.controller;

import com.example.test1.modele.DTO.CommandeDto;
import com.example.test1.modele.Entity.Commande;
import com.example.test1.security.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/command")
public class CommandeController {
    @Autowired
    private CommandeService commandeservice;

    @PostMapping("/enregistrerlocationForm")
    public String CreerCommande(@ModelAttribute("commande") @Validated CommandeDto commandeDto){
        commandeservice.save(commandeDto);
        return "redirect:paiement";
    }

    @GetMapping("/listecommande")
    public String listCommande(Model model, String nomR) {

        //recuperation de la liste des Enseignants

        final List<Commande> commande = commandeservice.listCommande(nomR);

        List<CommandeDto> dtos= new ArrayList<CommandeDto>();

        for (Commande commandes:commande) {
            CommandeDto commandeDto = new CommandeDto();
            commandeDto.setNbrplat(commandes.getNbrplat());
            commandeDto.setTotal(commandes.getTotal());
            commandeDto.setFraislivraison(commandes.getFraislivraison());
            commandeDto.setSousmontant(commandes.getSousmontant());
            commandeDto.setDate(commandes.getDate());
            commandeDto.setRestaurant(commandes.getRestaurant());
            commandeDto.setUser(commandes.getUser());

            dtos.add(commandeDto);
        }

        model.addAttribute("listCommande", dtos);

        return "listcommande";
    }
}
