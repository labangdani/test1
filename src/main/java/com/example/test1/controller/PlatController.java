package com.example.test1.controller;

import com.example.test1.modele.DTO.PlatDto;
import com.example.test1.modele.DTO.RestaurantDto;
import com.example.test1.modele.Entity.*;
import com.example.test1.repository.PlatRepository;
import com.example.test1.repository.RestaurantRepository;
import com.example.test1.repository.UtilisateurRepository;
import com.example.test1.security.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.context.SecurityContextHolder;

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

   @RequestMapping(value="/save", method = RequestMethod.POST)
    public String CreatePlat(@ModelAttribute("plat") @Validated PlatDto platDto, BindingResult result, Long idresto)
    {
        Plat existing = platRepository.findAllByNomP(platDto.getNomR());
        if(existing != null){
            result.rejectValue("nom", null, "There is already an account registered with that nom");
        }
        if (result.hasErrors()){
            return "enregistrerplat";
        }
        // Create new plat's account
        Plat plat = new Plat(platDto.getNomR(),
                platDto.getPrix(),
                platDto.getDescription(),
                platDto.getImage());


       Restaurant restaurant = restaurantRepository.findByIdresto(platDto.getRestaurant().getIdresto());

        platRepository.save(plat);
        return "redirect:/menuResto";
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public String edit (Model model, Long idplat){
        Plat plat = platService.findOne(idplat);
        model.addAttribute("plat", plat);
        return "EditPlat";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Long id){
        platRepository.deleteByIdplat(id);
        return "redirect:/menuResto";
    }

    @RequestMapping(value="/remplirPlatForm", method = RequestMethod.GET)
    public String pageEngregistrerPlat() {
        return "enregistrerplat";
    }

    @RequestMapping(value="/menuResto", method = RequestMethod.GET)
    public String affichermenu()
    {
        return "menuresto";
    }

    @RequestMapping(value="/listeplat/{nomR}", method = RequestMethod.GET)
    public String listPlats(@PathVariable(name = "nomR") String nomR, Model model) {

        //recuperation de la liste des Restaurants

        Restaurant restaurant = restaurantRepository.findByNomR(nomR);
        restaurant.getPlat();

        List<PlatDto> dtos = new ArrayList<PlatDto>();

        for (Plat plats : restaurant.getPlat()) {
            PlatDto platDto = new PlatDto();
            platDto.setNomR(plats.getNomP());
            platDto.setDescription(plats.getDescription());
            platDto.setImage(plats.getImage());
            platDto.setPrix(plats.getPrix());
            System.out.println("les images de mes restos sont : " + plats.getImage());

            dtos.add(platDto);
        }
        //enregistrement dans le model
        model.addAttribute("listPlat", dtos);
        return "menu";
    }

}
