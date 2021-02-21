package com.example.test1.controller;

import com.example.test1.modele.DTO.Command;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class OrderController {
    @RequestMapping(value="/test", method = RequestMethod.POST)
    public String greeting(@ModelAttribute("command") Command command) {
        System.out.println(command.getHeure());
        return "success";
    }

}
