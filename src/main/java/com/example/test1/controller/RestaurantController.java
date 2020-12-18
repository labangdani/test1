package com.example.test1.controller;

import com.example.test1.modele.DTO.RestaurantDto;
import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.modele.Entity.Role;
import com.example.test1.modele.Entity.Utilisateur;
import com.example.test1.repository.RestaurantRepository;
import com.example.test1.repository.UtilisateurRepository;
import com.example.test1.security.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("/resto")
public class RestaurantController {
    @Autowired
    private RestaurantService restoservice;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String RestoAccount(@ModelAttribute("restaurant") @Validated RestaurantDto restaurantDto, BindingResult result){
        Restaurant existing = restoservice.rechercherRestaurant(restaurantDto.getNomR(), restaurantDto.getLocalisation());
        if(existing != null){
            result.rejectValue("nom", null, "There is already an account registered with that matricule");
            result.rejectValue("localisation", null, "There is already an account registered with that matricule");
        }
        if (result.hasErrors()){
            return "enregistrerresto";
        }

        // Create new restaurant's account
        Restaurant restaurant = new Restaurant(restaurantDto.getNomR(),
                restaurantDto.getLocalisation(),
                restaurantDto.getImage(),
                restaurantDto.getDescription(),
                restaurantDto.getMailR(),
                restaurantDto.getTel(),
                restaurantDto.getType(),
                restaurantDto.getLundi(),
                restaurantDto.getMardi(),
                restaurantDto.getMercredi(),
                restaurantDto.getJeudi(),
                restaurantDto.getVendredi(),
                restaurantDto.getSamedi(),
                restaurantDto.getDimanche());

        Set<Utilisateur> utilisateurs = new HashSet<>();

        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());

        Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());

        System.out.println(utilisateur.getIdU());


        utilisateurs.add(utilisateur);
        restaurant.setUtilisateurs(utilisateurs);
        restaurantRepository.save(restaurant);
        return "menuresto";
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public String edit (Model model, Long idresto){
        Restaurant restaurant = restoservice.findOne(idresto);
        model.addAttribute("restaurant", restaurant);
        return "EditResto";
    }

    @RequestMapping(value="/remplirRestoForm", method=RequestMethod.GET)
    public String pageEngregistrerRestaurant(Model model) {

        model.addAttribute("restaurantDto", new RestaurantDto());

        return "enregistrerresto";
    }

    @RequestMapping(value="/listeresto", method = RequestMethod.GET)
    public String listRestaurants(Model model, String localisation) {

        //recuperation de la liste des Restaurants

        final List<Restaurant> restaurant = restoservice.rechercherlocalisation(localisation);

        List<RestaurantDto> dtos= new ArrayList<RestaurantDto>();

        for (Restaurant restaurants:restaurant) {
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setIdresto(restaurants.getIdresto());
            restaurantDto.setNomR(restaurants.getNomR());
            restaurantDto.setDescription(restaurants.getDescription());
            restaurantDto.setImage(restaurants.getImage());
            restaurantDto.setLocalisation(restaurants.getLocalisation());
            restaurantDto.setMailR(restaurants.getMailR());
            restaurantDto.setTel(restaurants.getTel());
            restaurantDto.setType(restaurants.getType());
            System.out.println("les images de mes restos sont : "+restaurants.getImage());

            dtos.add(restaurantDto);
        }

        //enregistrement dans le model
        model.addAttribute("listRestaurant", dtos);
        return "resto";
    }
}
