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
    public String CreatePlat(@ModelAttribute("plat") @Validated PlatDto platDto, BindingResult result)
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
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(auth.getName());
        Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());

        System.out.println(utilisateur.getIdU());

        List<String> restaurant = restaurantRepository.chercherresto(utilisateur.getIdU());
        System.out.println(restaurant);


//        Restaurant restaurant = restaurantRepository.findByIdresto(platDto.getRestaurant().getIdresto());

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

/*    @RequestMapping(value="/listeplat", method = RequestMethod.GET)
    public String listPlats(Model model, Long idresto) {

        //recuperation de la liste des Restaurants

        final List<Plat> plat = platService.rechercherplat(idresto);

        List<PlatDto> dtos = new ArrayList<PlatDto>();

        for (Plat plats : plat) {
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
    }*/

}
