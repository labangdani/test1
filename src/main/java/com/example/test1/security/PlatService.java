package com.example.test1.security;

import com.example.test1.modele.DTO.PlatDto;
import com.example.test1.modele.Entity.Plat;

import java.util.List;

public interface PlatService {
    public Plat Platidentique(String nomP);
    public Plat findOne(Long idplat);
}
