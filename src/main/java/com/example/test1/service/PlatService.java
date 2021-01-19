package com.example.test1.service;

import com.example.test1.modele.Entity.Plat;

public interface PlatService {
    public Plat Platidentique(String nomP);
    public Plat findOne(String nomp);
}
