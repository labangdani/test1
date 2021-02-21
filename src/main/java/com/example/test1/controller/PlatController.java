package com.example.test1.controller;

import com.example.test1.modele.DTO.PlatDto;
import com.example.test1.modele.Entity.*;
import com.example.test1.repository.PlatRepository;
import com.example.test1.repository.RestaurantRepository;
import com.example.test1.repository.UtilisateurRepository;
import com.example.test1.service.PlatService;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/plat")
public class PlatController {

    private final Path root = Paths.get("src\\main\\resources\\static\\images");

    @Autowired
    private PlatRepository platRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PlatService platService;

    @RequestMapping(value="/remplirPlatForm/{nomR}", method = RequestMethod.GET)
    public String pageEngregistrerPlat(@PathVariable(name = "nomR") String nomR, Model model) {
        model.addAttribute("nomR", nomR);
        return "enregistrerplat";
    }

   @RequestMapping(value = "/save/{nomR}", method = RequestMethod.POST)
    public String CreatePlat(@Validated PlatDto platDto, BindingResult result, @PathVariable(name= "nomR") String nomR, @RequestParam("image") MultipartFile multipartFile) throws IOException
    {
        Plat existing = platRepository.findAllByNomP(platDto.getNomP());
        if(existing != null){
            result.rejectValue("nom", null, "There is already an account registered with that nom");
        }
        
      /*  if (result.hasErrors()){
            return "enregistrerplat";
        }*/
        // Create new plat's account

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
        Plat plat = new Plat(platDto.getNomP(),
                platDto.getPrix(),
                platDto.getDescription());
        plat.setImage(fileName);
        System.out.println(fileName);
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
        System.out.println(plat);
        model.addAttribute("plat", plat);
        return "editPlatForm";
    }

    @RequestMapping(value = "/update/{nomR}", method = RequestMethod.POST)
    public String update (@Validated PlatDto platDto, @PathVariable("nomR") String nomR, @RequestParam("file") MultipartFile multipartFile)
    {
        System.out.println("bonjour");
     /*  if (result.hasErrors()){
            return "editPlatForm";
        }*/
        Plat plats = platService.findOne(platDto.getNomP());
        if(multipartFile.isEmpty() == false) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            System.out.println(fileName);

            if (Files.exists(this.root.resolve(multipartFile.getOriginalFilename())) == false) {
                try {
                    Files.copy(multipartFile.getInputStream(), this.root.resolve(multipartFile.getOriginalFilename()));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Could not store the file! error:" + e.getMessage());
                }
                plats.setImage(fileName);
            }

            plats.setNomP(platDto.getNomP());
            plats.setPrix(platDto.getPrix());
            plats.setDescription(platDto.getDescription());
            plats.setImage(fileName);
            Restaurant restaurant = restaurantRepository.findByNomR(nomR);
            plats.setRestaurants(restaurant);
            platRepository.save(plats);
        }
        else{
            plats.setNomP(platDto.getNomP());
            plats.setPrix(platDto.getPrix());
            plats.setDescription(platDto.getDescription());
            Restaurant restaurant = restaurantRepository.findByNomR(nomR);
            plats.setRestaurants(restaurant);
            platRepository.save(plats);
        }
        return "redirect:/plat/listeplat/{nomR}";
    }


    @RequestMapping(value = "/delete/{nomp}", method = RequestMethod.GET)
    public String delete(@PathVariable(name="nomp") String nomp, Model model){
        Plat plat = this.platRepository.findByNomP(nomp);
        this.platRepository.delete(plat);
        model.addAttribute("listPlat", this.platRepository.findAll());
        return "menuresto";
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
            platDto.setImage(plats.getPhotosImagePath());
            platDto.setPrix(plats.getPrix());
            platDto.setRestaurants(plats.getRestaurants());
            System.out.println("les images de mes restos sont : " + plats.getPhotosImagePath());

            dtos.add(platDto);
        }
        //enregistrement dans le model
        model.addAttribute("listPlat", dtos);
        model.addAttribute("restaurant", restaurant);
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
            platDto.setImage(plats.getPhotosImagePath());
            platDto.setPrix(plats.getPrix());
            platDto.setRestaurants(plats.getRestaurants());
            System.out.println("les images de mes restos sont : " + plats.getPhotosImagePath());

            dtos.add(platDto);
        }
        //enregistrement dans le model
        model.addAttribute("listPlat", dtos);
        model.addAttribute("nomR", nomR);
        return "menuresto";
    }

    @RequestMapping(value="/paiement/{nomR}", method = RequestMethod.GET)
    public String payer(Model model, @PathVariable(name = "nomR") String nomR) {
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());
        Restaurant restaurant = restaurantRepository.findByNomR(nomR);
        model.addAttribute("username", auth.getName());
        model.addAttribute("user", utilisateur);
        model.addAttribute("restaurantDto", restaurant);
        return "paiement";
    }
}
