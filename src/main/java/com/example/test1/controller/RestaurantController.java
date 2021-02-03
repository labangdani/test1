package com.example.test1.controller;

import com.example.test1.modele.DTO.RestaurantDto;
import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.modele.Entity.Utilisateur;
import com.example.test1.repository.RestaurantRepository;
import com.example.test1.repository.UtilisateurRepository;
import com.example.test1.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping("/resto")
public class RestaurantController {
    
    private final Path root = Paths.get("src\\main\\resources\\static\\images");

    @Autowired
    private RestaurantService restoservice;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String RestoAccount(@Validated RestaurantDto restaurantDto, BindingResult result, @RequestParam("image") MultipartFile multipartFile){
        Restaurant existing = restoservice.rechercherRestaurant(restaurantDto.getNomR(), restaurantDto.getLocalisation());
        if(existing != null){
            result.rejectValue("nom", null, "There is already an account registered with that matricule");
            result.rejectValue("localisation", null, "There is already an account registered with that matricule");
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(fileName);

        if(Files.exists(this.root.resolve(multipartFile.getOriginalFilename())) == false)
        {
            try {
                Files.copy(multipartFile.getInputStream(), this.root.resolve(multipartFile.getOriginalFilename()));
            } catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Could not store the file! error:" +e.getMessage());
            }
        }

        // Create new restaurant's account
        Restaurant restaurant = new Restaurant(restaurantDto.getNomR(),
                restaurantDto.getLocalisation(),
                restaurantDto.getDescription(),
                restaurantDto.getMailR(),
                restaurantDto.getTel(),
                restaurantDto.getType(),
                restaurantDto.getFraisdelivraison(),
                restaurantDto.getLundi(),
                restaurantDto.getMardi(),
                restaurantDto.getMercredi(),
                restaurantDto.getJeudi(),
                restaurantDto.getVendredi(),
                restaurantDto.getSamedi(),
                restaurantDto.getDimanche());

                restaurant.setImage(fileName);

        Set<Utilisateur> utilisateurs = new HashSet<>();

        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());

        Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());

        System.out.println(utilisateur.getIdU());
        utilisateurs.add(utilisateur);
        restaurant.setUtilisateurs(utilisateurs);
        restaurantRepository.save(restaurant);
        return "redirect:/resto/allresto";
    }

       @RequestMapping(value = "/edit/{nomR}", method = RequestMethod.GET)
    public String edit (Model model, @PathVariable(name="nomR") String nomr){
        Restaurant restaurant = restoservice.findNom(nomr);
        model.addAttribute("restaurant", restaurant);
        return "editRestoForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String save (@Validated Restaurant restaurant, BindingResult result, @RequestPart("image") MultipartFile multipartFile){


       if (result.hasErrors()){
            return "editRestoForm";
        }
        Restaurant restaurants = restoservice.findNom(restaurant.getNomR());
        System.out.println(restaurant);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(fileName);
        if(Files.exists(this.root.resolve(multipartFile.getOriginalFilename())) == false) {
            try {
                Files.copy(multipartFile.getInputStream(), this.root.resolve(multipartFile.getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Could not store the file! error:" + e.getMessage());
            }
        }

                    // update restaurant's account
                    restaurants.setNomR(restaurant.getNomR());
                    restaurants.setLocalisation(restaurant.getLocalisation());
                    restaurants.setDescription(restaurant.getDescription());
                    restaurants.setMailR(restaurant.getMailR());
                    restaurants.setTel(restaurant.getTel());
                    restaurants.setType(restaurant.getType());
                    restaurants.setFraisdelivraison(restaurant.getFraisdelivraison());
                    restaurants.setLundi(restaurant.getLundi());
                    restaurants.setMardi(restaurant.getMardi());
                    restaurants.setMercredi(restaurant.getMercredi());
                    restaurants.setJeudi(restaurant.getJeudi());
                    restaurants.setVendredi(restaurant.getVendredi());
                    restaurants.setSamedi(restaurant.getSamedi());
                    restaurants.setDimanche(restaurant.getDimanche());
                    restaurants.setImage(fileName);

                    Set<Utilisateur> utilisateurs = new HashSet<>();

                    Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
                    System.out.println(auth.getName());

                    Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());

                    System.out.println(utilisateur.getIdU());


                    utilisateurs.add(utilisateur);
                    restaurants.setUtilisateurs(utilisateurs);
                    restaurantRepository.save(restaurants);

        return "redirect:/resto/allresto";

    }

    @RequestMapping(value = "/allresto", method = RequestMethod.GET)
    public String listeresto(Model model){
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());

        Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());

        System.out.println(utilisateur.getIdU());
        utilisateur.getRestaurants();
        List<RestaurantDto> dtos= new ArrayList<RestaurantDto>();
        for (Restaurant restaurants:utilisateur.getRestaurants()) {
            RestaurantDto restoDto = new RestaurantDto();
            restoDto.setIdresto(restaurants.getIdresto());
            restoDto.setNomR(restaurants.getNomR());
            restoDto.setDescription(restaurants.getDescription());
            restoDto.setImage(restaurants.getPhotosImagePath());
            restoDto.setLocalisation(restaurants.getLocalisation());
            restoDto.setMailR(restaurants.getMailR());
            restoDto.setTel(restaurants.getTel());
            restoDto.setType(restaurants.getType());
            restoDto.setFraisdelivraison(restaurants.getFraisdelivraison());
            System.out.println("les images de mes restos sont : "+restaurants.getPhotosImagePath());

            dtos.add(restoDto);
        }

        //enregistrement dans le model
        model.addAttribute("listResto", dtos);
        model.addAttribute("username", auth.getName());

        return "listeresto";
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
            restaurantDto.setImage(restaurants.getPhotosImagePath());
            restaurantDto.setLocalisation(restaurants.getLocalisation());
            restaurantDto.setMailR(restaurants.getMailR());
            restaurantDto.setTel(restaurants.getTel());
            restaurantDto.setType(restaurants.getType());
            restaurantDto.setFraisdelivraison(restaurants.getFraisdelivraison());
            System.out.println("les images de mes restos sont : "+restaurants.getImage());

            dtos.add(restaurantDto);
        }

        //enregistrement dans le model
        model.addAttribute("listRestaurant", dtos);
        return "resto";
    }
}
