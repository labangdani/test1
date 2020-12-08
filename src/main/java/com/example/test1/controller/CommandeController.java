package com.example.test1.controller;

import com.example.test1.modele.DTO.CommandeDto;
import com.example.test1.modele.Entity.Commande;
import com.example.test1.security.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


@Controller
@RequestMapping("/command")
public class CommandeController {
    @Autowired
    private CommandeService commandeservice;

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String CreerCommande(@ModelAttribute("commande") @Validated CommandeDto commandeDto){
        commandeservice.save(commandeDto);
        return "redirect:paiement";
    }

    @RequestMapping(value="/listecommande", method = RequestMethod.GET)
    public String listCommande(Model model, String nomR, @RequestParam(name = "page", defaultValue = "0")int p,
                               @RequestParam(name = "size", defaultValue = "5")int s) {

        //recuperation de la liste des Enseignants
        Page<Commande> commande = commandeservice.listCommande(nomR, new PageRequest(p,s));

        Page<CommandeDto> dtos= new Page<CommandeDto>() {
            @Override
            public Iterator<CommandeDto> iterator() {
                return null;
            }

            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super CommandeDto, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<CommandeDto> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

        };

  /*      for (Commande commandes:commande) {
            CommandeDto commandeDto = new CommandeDto();
            commandeDto.setNbrplat(commandes.getNbrplat());
            commandeDto.setTotal(commandes.getTotal());
            commandeDto.setFraislivraison(commandes.getFraislivraison());
            commandeDto.setSousmontant(commandes.getSousmontant());
            commandeDto.setDate(commandes.getDate());
            commandeDto.setRestaurant(commandes.getRestaurant());
            commandeDto.setUser(commandes.getUser());

            dtos.add(commandeDto);
        }*/

        model.addAttribute("listCommande", dtos.getContent());
        int[] pages = new int [dtos.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("size", s);
        model.addAttribute("pageCourante", p);

        return "listcommande";
    }
}
