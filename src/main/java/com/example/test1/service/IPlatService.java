package com.example.test1.service;

import com.example.test1.modele.Entity.Plat;
import com.example.test1.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPlatService implements PlatService {
    @Autowired
    private PlatRepository platRepository;

    @Override
    public Plat Platidentique(String nomP) {
        return platRepository.findAllByNomP(nomP);
    }

    @Override
    public Plat findOne(String nomp) {
        return platRepository.findByNomP(nomp);
    }


}
