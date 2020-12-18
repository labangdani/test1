package com.example.test1.controller;

import com.example.test1.modele.DTO.RestaurantDto;
import com.example.test1.modele.DTO.UserDetailsImpl;
import com.example.test1.modele.DTO.UtilisateurDto;
import com.example.test1.modele.Entity.ERole;
import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.modele.Entity.Role;
import com.example.test1.modele.Entity.Utilisateur;
import com.example.test1.repository.RoleRepository;
import com.example.test1.repository.UtilisateurRepository;
import com.example.test1.security.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;


@Controller
@RequestMapping("/User")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurservice;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value="/remplirUserForm", method = RequestMethod.GET)
    public String pageEngregistrerUtilisateur() {
        return "enregistrerclient";
    }

    @RequestMapping(value="/connexion", method = RequestMethod.GET)
    public String pagedeconnexion() {

        return "login";
    }

    @RequestMapping(value="/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public String edit (Model model, Long idu){
        Utilisateur utilisateur = utilisateurservice.findOne(idu);
        model.addAttribute("utilisateur", utilisateur);
        return "EditUser";
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String home() {
        return "indexlog";
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("utilisateur") @Validated UtilisateurDto utilisateurDto, BindingResult result) {
        System.out.println("Bonjour");
        if (utilisateurRepository.existsByUsername(utilisateurDto.getUsername())) {
            result.rejectValue("Username", null, "Username is already taken!");
        }

        if (utilisateurRepository.existsByMailU(utilisateurDto.getMailU())) {
            result.rejectValue("MailU", null, "MailU is already taken!");
        }

        // Create new user's account
        Utilisateur utilisateur = new Utilisateur(utilisateurDto.getUsername(),
                utilisateurDto.getMailU(),
                encoder.encode(utilisateurDto.getPassword()),
                utilisateurDto.getTelU(),
                utilisateurDto.getSexe());

        Set<String> strRoles = utilisateurDto.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        utilisateur.setRoles(roles);
        utilisateurRepository.save(utilisateur);
        return "redirect:connexion";
    }

    @RequestMapping(value="/listeuser", method = RequestMethod.GET)
    public String listUtilisateur(Model model, String username, String password) {

        //recuperation de la liste des Restaurants

        final Utilisateur utilisateur = utilisateurRepository.findByUsernameAndPassword(username, password);

        UtilisateurDto dtos= new UtilisateurDto();

        dtos.setUsername(utilisateur.getUsername());
        dtos.setMailU(utilisateur.getMailU());
        dtos.setPassword(utilisateur.getPassword());
        dtos.setTelU(utilisateur.getTelU());
        dtos.setSexe(utilisateur.getSexe());
        System.out.println("l'utilisateur est : "+utilisateur.getUsername());


        //enregistrement dans le model
        model.addAttribute("Utilisateur", dtos);
        return "indexlog";
    }
   /* @RequestMapping(value="/signin", method = RequestMethod.POST)
    public String authenticateUser(@ModelAttribute("utilisateur")@Valid UtilisateurDto utilisateurDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(utilisateurDto.getUsername(), utilisateurDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return "redirect:home";
    }*/
}
