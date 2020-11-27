package com.example.test1.controller;

import com.example.test1.modele.DTO.PlatDto;
import com.example.test1.modele.Entity.Plat;
import com.example.test1.security.service.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plat")
public class PlatController {
    @Autowired
    private PlatService platservice;

    @PostMapping("/enregistrerPlat")
    public String RestoAccount(@ModelAttribute("plat") @Validated PlatDto platDto,
                               BindingResult result){
        Plat existing = platservice.Platidentique(platDto.getNomR());
        if(existing != null){
            result.rejectValue("nom", null, "There is already an account registered with that nom");
        }
        if (result.hasErrors()){
            return "enregistrerplat";
        }
        platservice.save(platDto);
        return "redirect:menuresto";
    }

}
