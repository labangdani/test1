package com.example.test1.security.service;

import com.example.test1.modele.DTO.PlatDto;
import com.example.test1.modele.Entity.Plat;

public interface PlatService {
    public Plat save(PlatDto platDto);
    public Plat Platidentique(String nomP);
}
