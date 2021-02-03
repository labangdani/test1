package com.example.test1.controller;

import com.example.test1.modele.Entity.Panier;
import com.example.test1.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@Controller
public class PanierController {
    @Autowired
    private PanierRepository panierRepository;

	@RequestMapping(value= {"/panier/save"}, method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String panierSave(@RequestBody Panier data) {
		String result = "ko";
		String nomplat = data.getNomplat();
		int prix = data.getPrix();
        int quantite = data.getQty();

		System.out.println("data="+data+";np="+nomplat+";px="+prix+";qty="+quantite );
		/*Panier  = mailListRepository.findFirstByEmail(email);
		if(check != null) {
			result = "already";
			check.setState(MailList.STATE_ACTIF);
			mailListRepository.save(check);
		}else {
	    	*//*MailList ml = new MailList();
	    	ml.setEmail(email);
	    	ml.setNom(nom);*//*
	    	data.setState(MailList.STATE_ACTIF);
	    	data.setType(MailList.TYPE_VISITEUR);
			data.setSubscription_date(new Date(System.currentTimeMillis()));
			mailListRepository.save(data);
			result = "ok";
		}*/
		return "{  \"response\" : \""+ result +"\" }";
	}

	@RequestMapping(value="/panier/test", method=RequestMethod.POST)
	public String test(@RequestBody Panier data) {
		System.out.println(data);

		return "demo";
	}
}