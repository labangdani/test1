package com.example.test1.repository;

import com.example.test1.modele.Entity.Lignecommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LignecommandeRepository extends JpaRepository<Lignecommande, Long> {

    boolean existsById(Long id);

}
