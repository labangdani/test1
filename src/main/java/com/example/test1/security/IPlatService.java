package com.example.test1.security;

import com.example.test1.modele.DTO.PlatDto;
import com.example.test1.modele.Entity.Plat;
import com.example.test1.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPlatService implements PlatService {
    @Autowired
    private PlatRepository platRepository;

    @Override
    public Plat Platidentique(String nomP) {
        return platRepository.findAllByNomP(nomP);
    }

    /*@Override
    public List<Plat> rechercherplat(Long idresto) {
        return platRepository.rechercherplats(idresto);
    }*/

    @Override
    public Plat findOne(Long idplat) {
        return platRepository.findByIdplat(idplat);
    }


}
