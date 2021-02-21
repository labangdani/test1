package com.example.test1.service;

import com.example.test1.modele.Entity.Localisation;
import com.example.test1.repository.LocalisationRepository;
import com.example.test1.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ILocalisationService implements LocalisationService {
    @Autowired
    private LocalisationRepository localisationRepository;

   /* @Override
    public List<Localisation> rechercherlistLocal(String localisation) {
        return localisationRepository.listlocalisation(localisation);
    }*/
}
