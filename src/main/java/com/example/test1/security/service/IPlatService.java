package com.example.test1.security.service;

import com.example.test1.modele.DTO.PlatDto;
import com.example.test1.modele.Entity.Plat;
import com.example.test1.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPlatService implements PlatService {
    @Autowired
    private PlatRepository platRepository;

    @Override
    public Plat save(PlatDto platDto) {
        Plat plat = new Plat();
        plat.setIdplat(platDto.getIdplat());
        plat.setDescription(platDto.getDescription());
        plat.setImage(platDto.getImage());
        plat.setNomP(platDto.getNomR());
        plat.setPrix(platDto.getPrix());
        plat.setRestaurant(platDto.getRestaurant());
        return platRepository.save(plat);
    }

    @Override
    public Plat Platidentique(String nomP) {
        return platRepository.findAllByNomP(nomP);
    }
}
