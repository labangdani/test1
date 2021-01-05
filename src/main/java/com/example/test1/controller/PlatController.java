package com.example.test1.controller;

import com.example.test1.modele.DTO.PlatDto;
import com.example.test1.modele.Entity.*;
import com.example.test1.repository.PlatRepository;
import com.example.test1.repository.RestaurantRepository;
import com.example.test1.repository.UtilisateurRepository;
import com.example.test1.security.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/plat")
public class PlatController {

    @Autowired
    private PlatRepository platRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PlatService platService;

    @RequestMapping(value = "/info/{nomp}")
    public String showinfoplat(@PathVariable(name = "nomp")String nomp, Model model){
        Plat plat = platRepository.findByNomP(nomp);
        System.out.println(plat);
        model.addAttribute("platinfo", plat);
        return "menu";
    }

   @RequestMapping(value = "/save/{nomR}", method = RequestMethod.POST)
    public String CreatePlat(@Validated PlatDto platDto, BindingResult result, @PathVariable(name= "nomR") String nomR)
    {
        Plat existing = platRepository.findAllByNomP(platDto.getNomP());
        if(existing != null){
            result.rejectValue("nom", null, "There is already an account registered with that nom");
        }
        if (result.hasErrors()){
            return "enregistrerplat";
        }
        // Create new plat's account
        Plat plat = new Plat(platDto.getNomP(),
                platDto.getPrix(),
                platDto.getDescription(),
                platDto.getImage());
        Restaurant restaurant= restaurantRepository.findByNomR(nomR);
        System.out.println(restaurant);
        plat.setRestaurants(restaurant);
        platRepository.save(plat);
        return "redirect:/plat/listeplat/{nomR}";
    }

    @RequestMapping(value = "/edit/{nomp}", method = RequestMethod.GET)
    public String edit (Model model, @PathVariable(name="nomp") String nomp){
        Plat plat = platService.findOne(nomp);
        System.out.println(plat.getRestaurants().getNomR());
        model.addAttribute("plat", plat);
        return "editPlatForm";
    }

    @RequestMapping(value = "/update/{nomR}", method = RequestMethod.POST)
    public String save (@Validated Plat plat, @PathVariable(name = "nomR") String nomR, BindingResult result){

       if (result.hasErrors()){
            return "editPlatForm";
        }
       Plat plats = platService.findOne(plat.getNomP());
       plats.setNomP(plat.getNomP());
       plats.setPrix(plat.getPrix());
       plats.setDescription(plat.getDescription());
       plats.setImage(plat.getImage());
       Restaurant restaurant = restaurantRepository.findByNomR(nomR);
        plat.setRestaurants(restaurant);
        platRepository.save(plats);
        return "redirect:/plat/listeplat/{nomR}";

    }


    @RequestMapping(value = "/delete/{nomp}", method = RequestMethod.GET)
    public String delete(@PathVariable(name="nomp") String nomp, Model model){
        Plat plat = this.platRepository.findByNomP(nomp);
        this.platRepository.delete(plat);
        model.addAttribute("listPlat", this.platRepository.findAll());
        return "menuresto";
    }


    @RequestMapping(value="/remplirPlatForm/{nomR}", method = RequestMethod.GET)
    public String pageEngregistrerPlat(@PathVariable(name = "nomR") String nomR, Model model) {
        model.addAttribute("nomR", nomR);
        return "enregistrerplat";
    }

    @RequestMapping(value="/menuResto/{nomR}", method = RequestMethod.GET)
    public String pageMenu(@PathVariable(name = "nomR") String nomR, Model model) {
        //recuperation de la liste des Restaurants

        Restaurant restaurant = restaurantRepository.findByNomR(nomR);
        restaurant.getPlat();

        List<PlatDto> dtos = new ArrayList<PlatDto>();

        for (Plat plats : restaurant.getPlat()) {
            PlatDto platDto = new PlatDto();
            platDto.setNomP(plats.getNomP());
            platDto.setDescription(plats.getDescription());
            platDto.setImage(plats.getImage());
            platDto.setPrix(plats.getPrix());
            platDto.setRestaurant(plats.getRestaurants());
            System.out.println("les images de mes restos sont : " + plats.getImage());

            dtos.add(platDto);
        }
        //enregistrement dans le model
        model.addAttribute("listPlat", dtos);
        model.addAttribute("nomR", nomR);
        return "menu";
    }

    

    @RequestMapping(value="/listeplat/{nomR}", method = RequestMethod.GET)
    public String listPlats(@PathVariable(name = "nomR") String nomR, Model model) {

        //recuperation de la liste des Restaurants

        Restaurant restaurant = restaurantRepository.findByNomR(nomR);
        restaurant.getPlat();

        List<PlatDto> dtos = new ArrayList<PlatDto>();

        for (Plat plats : restaurant.getPlat()) {
            PlatDto platDto = new PlatDto();
            platDto.setNomP(plats.getNomP());
            platDto.setDescription(plats.getDescription());
            platDto.setImage(plats.getImage());
            platDto.setPrix(plats.getPrix());
            platDto.setRestaurant(plats.getRestaurants());
            System.out.println("les images de mes restos sont : " + plats.getImage());

            dtos.add(platDto);
        }
        //enregistrement dans le model
        model.addAttribute("listPlat", dtos);
        model.addAttribute("nomR", nomR);
        return "menuresto";
    }

    @RequestMapping(value="/paiement", method = RequestMethod.GET)
    public String payer(Model model) {
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());
        model.addAttribute("username", auth.getName());
        model.addAttribute("user", utilisateur);
        return "paiement";
    }
}
