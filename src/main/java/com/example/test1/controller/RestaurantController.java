package com.example.test1.controller;

import com.example.test1.modele.DTO.RestaurantDto;
import com.example.test1.modele.Entity.Localisation;
import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.modele.Entity.Utilisateur;
import com.example.test1.repository.LocalisationRepository;
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
    @Autowired
    private LocalisationRepository localisationRepository;


    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String RestoAccount(@Validated RestaurantDto restaurantDto, BindingResult result, @RequestParam("image") MultipartFile multipartFile){
        Restaurant existing = restaurantRepository.findByNomR(restaurantDto.getNomR());
        if(existing != null){
            result.rejectValue("nom", null, "There is already an account registered with that matricule");
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
                restaurant.setLocalisations(restaurantDto.getLocalisations());
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
        List<Localisation> data = localisationRepository.selectall();
           model.addAttribute("data", data);
           model.addAttribute("restaurantDto", restaurant);
        return "editRestoForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String save (@Validated RestaurantDto restaurantDto, BindingResult result, @RequestParam("image") MultipartFile multipartFile){
      /*   if (result.hasErrors()){
            return "editRestoForm";
        } */
        Restaurant restaurants = restoservice.findNom(restaurantDto.getNomR());
        System.out.println(restaurants);
        if(multipartFile.isEmpty() == false){
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                System.out.println(fileName);

                if(Files.exists(this.root.resolve(multipartFile.getOriginalFilename())) == false) {
                    try {
                        Files.copy(multipartFile.getInputStream(), this.root.resolve(multipartFile.getOriginalFilename()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Could not store the file! error:" + e.getMessage());
                    }
                    restaurants.setImage(fileName);
                }

                    // update restaurant's account
                    restaurants.setNomR(restaurantDto.getNomR());
                    restaurants.setDescription(restaurantDto.getDescription());
                    restaurants.setMailR(restaurantDto.getMailR());
                    restaurants.setTel(restaurantDto.getTel());
                    restaurants.setType(restaurantDto.getType());
                    restaurants.setFraisdelivraison(restaurantDto.getFraisdelivraison());
                    restaurants.setLundi(restaurantDto.getLundi());
                    restaurants.setMardi(restaurantDto.getMardi());
                    restaurants.setMercredi(restaurantDto.getMercredi());
                    restaurants.setJeudi(restaurantDto.getJeudi());
                    restaurants.setVendredi(restaurantDto.getVendredi());
                    restaurants.setSamedi(restaurantDto.getSamedi());
                    restaurants.setDimanche(restaurantDto.getDimanche());
                     restaurants.setLocalisations(restaurantDto.getLocalisations());

            restaurants.setImage(fileName);


                    Set<Utilisateur> utilisateurs = new HashSet<>();

                    Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
                    System.out.println(auth.getName());

                    Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());
                    System.out.println(utilisateur.getIdU());

                    utilisateurs.add(utilisateur);
                    restaurants.setUtilisateurs(utilisateurs);
                    restaurantRepository.save(restaurants);}
        else{

            // update restaurant's account
            restaurants.setNomR(restaurantDto.getNomR());
            restaurants.setDescription(restaurantDto.getDescription());
            restaurants.setMailR(restaurantDto.getMailR());
            restaurants.setTel(restaurantDto.getTel());
            restaurants.setType(restaurantDto.getType());
            restaurants.setFraisdelivraison(restaurantDto.getFraisdelivraison());
            restaurants.setLundi(restaurantDto.getLundi());
            restaurants.setMardi(restaurantDto.getMardi());
            restaurants.setMercredi(restaurantDto.getMercredi());
            restaurants.setJeudi(restaurantDto.getJeudi());
            restaurants.setVendredi(restaurantDto.getVendredi());
            restaurants.setSamedi(restaurantDto.getSamedi());
            restaurants.setDimanche(restaurantDto.getDimanche());

            Set<Utilisateur> utilisateurs = new HashSet<>();

            Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth.getName());

            Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());
            System.out.println(utilisateur.getIdU());

            utilisateurs.add(utilisateur);
            restaurants.setUtilisateurs(utilisateurs);
            restaurantRepository.save(restaurants);
        }
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
        List<Localisation> data = localisationRepository.selectall();
        System.out.println(data);
        model.addAttribute("data", data);
        model.addAttribute("restaurantDto", new RestaurantDto());

        return "enregistrerresto";
    }

    @RequestMapping(value="/listeresto", method = RequestMethod.GET)
    public String listRestaurants(Model model, Long local) {

        //recuperation de la liste des Restaurants
        final Localisation localisation = localisationRepository.findByIdL(local);
        List<Restaurant> restaurant = localisation.getRestaurant();

        List<RestaurantDto> dtos= new ArrayList<RestaurantDto>();

        for (Restaurant restaurants:restaurant) {
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setIdresto(restaurants.getIdresto());
            restaurantDto.setNomR(restaurants.getNomR());
            restaurantDto.setDescription(restaurants.getDescription());
            restaurantDto.setImage(restaurants.getPhotosImagePath());
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

    @GetMapping("/detailresto/{id}")
    public String detailRestaurant(@PathVariable(name = "id") Long id, Model model) {

        //recuperation de la liste des Enseignants
        System.out.println("bonjour");
        final Restaurant restaurant = restoservice.findOne(id);

        //RestaurantDto dtos = new RestaurantDto();


        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setNomR(restaurant.getNomR());
        restaurantDto.setImage(restaurant.getPhotosImagePath());
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setTel(restaurant.getTel());
        restaurantDto.setLocalisations(restaurant.getLocalisations());






		/*final List<VehiculeDTO> dtos= Lists.transform(enseignants,
				(Enseignant input) -> new EnseignantDTO(input.getNom(),
						input.getTelephone(),input.getUe()));
*/

        //en registrement dans le model
        model.addAttribute("restaurant", restaurantDto);

        return "benana";
    }

}
