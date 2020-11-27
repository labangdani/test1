package com.example.test1.controller;

import com.example.test1.modele.DTO.UtilisateurDto;
import com.example.test1.modele.Entity.Utilisateur;
import com.example.test1.security.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/User")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurservice;

    @PostMapping("/enregistrerUser")
    public String UserAccount(@ModelAttribute("utilisateur") @Validated UtilisateurDto utilisateurDto,
                               BindingResult result){
        Utilisateur existing = utilisateurservice.rechercherUtilisateur(utilisateurDto.getMailU());
        if(existing != null){
            result.rejectValue("mail", null, "There is already an account registered with that matricule");
        }
        if (result.hasErrors()){
            return "enregistrerclient";
        }
        utilisateurservice.save(utilisateurDto);
        return "redirect:connexion";
    }

    @GetMapping("/remplirUserForm")
    public String pageEngregistrerUtilisateur(Model model) {

        model.addAttribute("utilisateurDto", new UtilisateurDto());

        return "enregistrerclient";
    }

    @GetMapping("/connexion")
    public String pagedeconnexion() {

        return "login";
    }
}
