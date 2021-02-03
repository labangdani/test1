package com.example.test1.repository;

import com.example.test1.modele.Entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Long> {

    Boolean existsByIdpanier(Long idpanier);

}
