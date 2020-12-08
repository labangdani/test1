package com.example.test1.controller;

import com.example.test1.modele.DTO.PlatDto;
import com.example.test1.modele.Entity.Plat;
import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.repository.PlatRepository;
import com.example.test1.security.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/plat")
public class PlatController {
    @Autowired
    private PlatService platservice;
    @Autowired
    private PlatRepository platRepository;

    @RequestMapping(value="/enregistrerPlat", method = RequestMethod.POST)
    public String RestoAccount(@ModelAttribute("plat") @Valid PlatDto platDto,
                               BindingResult result){
        Plat existing = platservice.Platidentique(platDto.getNomR());
        if(existing != null){
            result.rejectValue("nom", null, "There is already an account registered with that nom");
        }
        if (result.hasErrors()){
            return "enregistrerplat";
        }
        platservice.save(platDto);
        return "redirect:/menuResto";
    }
    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public String edit (Model model, Long idplat){
        Plat plat = platservice.findOne(idplat);
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

    @RequestMapping("/menuResto")
    public String affichermenu() {
        return "menuresto";
    }





}
