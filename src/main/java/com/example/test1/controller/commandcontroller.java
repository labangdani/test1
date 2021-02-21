package com.example.test1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping()
public class commandcontroller {

    @RequestMapping(value="/command/test", method = RequestMethod.GET)
    public String listcommand(@RequestBody String nom) {
        System.out.println(nom);
        return "success";
    }
}
