package com.example.test1.controller;

import com.example.test1.modele.DTO.CommandeDto;
import com.example.test1.modele.Entity.Commande;
import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.modele.Entity.Utilisateur;
import com.example.test1.repository.RestaurantRepository;
import com.example.test1.repository.UtilisateurRepository;
import com.example.test1.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/command")
public class CommandeController {
    @Autowired
    private CommandeService commandeservice;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String CreerCommande(@ModelAttribute("commande") @Validated CommandeDto commandeDto){
        commandeservice.save(commandeDto);
        return "redirect:paiement";
    }
    @RequestMapping(value="/listecommand/{nomR}", method = RequestMethod.GET)
    public String listRestaurants(Model model, @PathVariable(name="nomR") String nomresto) {

        Restaurant restaurant = restaurantRepository.findByNomR(nomresto);
        //recuperation de la liste des Restaurants
        final List<Commande> commande = restaurant.getCommande();

        List<CommandeDto> dtos= new ArrayList<CommandeDto>();

        for (Commande commandes:commande) {
            CommandeDto commandeDto = new CommandeDto();
            commandeDto.setUser(commandes.getUser());
            commandeDto.setNbrplat(commandes.getNbrplat());
            commandeDto.setTotal(commandes.getTotal());

            dtos.add(commandeDto);
        }

        //enregistrement dans le model
        model.addAttribute("listecommande", dtos);
        return "listcommande";
    }

    @RequestMapping(value="/usercommand", method = RequestMethod.GET)
    public String listCommande(Model model) {

        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur= utilisateurRepository.findByUsername(auth.getName());
        //recuperation de la liste des Restaurants
        final List<Commande> commande = utilisateur.getCommande();

        List<CommandeDto> dtos = new ArrayList<CommandeDto>();

        for (Commande commandes:commande) {
            CommandeDto commandeDto = new CommandeDto();
            commandeDto.setUser(commandes.getUser());
            commandeDto.setNbrplat(commandes.getNbrplat());
            commandeDto.setTotal(commandes.getTotal());

            dtos.add(commandeDto);
        }

        //enregistrement dans le model
        model.addAttribute("listecommande", dtos);
        return "listcommande";
    }
}
