package com.example.test1.controller;

import com.example.test1.modele.DTO.CommandeDto;
import com.example.test1.modele.Entity.*;
import com.example.test1.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Map;

@Controller
public class LignecommandeController {

    @Autowired
    private LocalisationRepository localisationRepository;
	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;


	@RequestMapping(value= {"/panier/save"}, method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String panierSave(@RequestBody Lignecommande data) {
		String result = "ko";
        int quantite = data.getQty();

		System.out.println("data="+data+";qty="+quantite );
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

	@RequestMapping(value="/panier/test", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String test(@RequestParam String commandDto, @RequestParam String data) {
		System.out.println(data);
		System.out.println(commandDto);

		/*Plat plat = data.getPlat();
		Restaurant restaurant = plat.getRestaurants();
		System.out.println(commandDto);
		Commande existing = commandeRepository.findAllByIdcom(commandDto.getIdcom());
		if(existing != null){
            return "Cette commande existe déjà";
		}

		Commande commande = new Commande(
				commandDto.getTotal(),
				commandDto.getCurrency(),
				commandDto.getIntent(),
				commandDto.isValidite(),
				commandDto.getHeure(),
				commandDto.getMode(),
				commandDto.getAdresse(),
				commandDto.getVille(),
				commandDto.getDatedujour(),
				commandDto.getRemarque(),
				commandDto.getNom_entreprise());

		Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		Utilisateur utilisateur = utilisateurRepository.findByUsername(auth.getName());
		commande.setUtilisateur(utilisateur);
		System.out.println(restaurant);
		commande.setRestaurants(restaurant);
		Commande cmd = commandeRepository.save(commande);
        System.out.println(cmd);
		for (int i = 0; i <= 5; i++){

		}*/

		return "success";
	}

	@RequestMapping(value= {"/panier/search"}, method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Localisation> read(@RequestParam String localisation) {
		String result = "ko";
		List<Localisation> data = localisationRepository.listlocalisation(localisation);
		Map<String, String> list = null;
		List<String> tab = null;
		System.out.println(data.get(0).getIdL() +", 'text': "+ data.get(0).getNomL());

//		if(data != null) {
//			result = "ok";
//		}else {
//			result = "ko";
//			return "{  \"response\" : \""+ result +"\" }";
//		}

		/*String r = "";
		for (String nomLocalisation : data) {
			r = r + nomLocalisation + ",";
		}*/
		System.out.println(tab);
		return data;
	}

}