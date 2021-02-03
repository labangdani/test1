package com.example.test1.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class commandcontroller {

    @RequestMapping(value="/test", method = RequestMethod.POST)
    public String greeting(@RequestBody String nom) {
        System.out.println(nom);
        return "success";
    }
}
