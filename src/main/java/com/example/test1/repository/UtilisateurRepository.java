package com.example.test1.repository;

import com.example.test1.modele.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    public Utilisateur findByMailU(String mailU);

    Optional<Utilisateur> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByMailU(String mailu);
}
