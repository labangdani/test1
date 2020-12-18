package com.example.test1.repository;


import com.example.test1.modele.Entity.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface
CommandeRepository extends JpaRepository<Commande, Long> {

    public Page<Commande> findAllByRestaurant(String nomR, Pageable pageable);

}
