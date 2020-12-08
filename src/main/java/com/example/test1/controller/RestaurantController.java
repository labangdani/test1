package com.example.test1.controller;

import com.example.test1.modele.DTO.RestaurantDto;
import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.security.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/resto")
public class RestaurantController {
    @Autowired
    private RestaurantService restoservice;

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String RestoAccount(@ModelAttribute("restaurant") @Valid RestaurantDto restaurantDto,
                                  BindingResult result){
        Restaurant existing = restoservice.rechercherRestaurant(restaurantDto.getNomR(), restaurantDto.getLocalisation());
        if(existing != null){
            result.rejectValue("nom", null, "There is already an account registered with that matricule");
            result.rejectValue("localisation", null, "There is already an account registered with that matricule");
        }
        if (result.hasErrors()){
            return "enregistrerresto";
        }
        restoservice.save(restaurantDto);
        return "menuresto";
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public String edit (Model model, Long idresto){
        Restaurant restaurant = restoservice.findOne(idresto);
        model.addAttribute("restaurant", restaurant);
        return "EditResto";
    }

    @RequestMapping(value="/remplirForm", method=RequestMethod.GET)
    public String pageEngregistrerRestaurant(Model model) {

        model.addAttribute("restaurantDto", new RestaurantDto());

        return "enregistrerresto";
    }

    /*@GetMapping("/listeresto")
    public String listRestaurants(Model model, String localisation) {

        //recuperation de la liste des Enseignants

        final List<Restaurant> restaurant = restoservice.listRestaurant(localisation);

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
    }*/
}
