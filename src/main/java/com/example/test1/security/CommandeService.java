package com.example.test1.security;

import com.example.test1.modele.DTO.CommandeDto;
import com.example.test1.modele.Entity.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommandeService {
    public Commande save(CommandeDto commandeDto);
}
