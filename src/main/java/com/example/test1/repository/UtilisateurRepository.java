package com.example.test1.repository;

import com.example.test1.modele.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    public Utilisateur findByMailU(String mailU);
    public Utilisateur findByIdU(Long idu);

   public  Utilisateur findByUsername(String username);
    public Utilisateur findByUsernameAndPassword(String username, String password);
    Boolean existsByUsername(String username);

    Boolean existsByMailU(String mailu);
}
