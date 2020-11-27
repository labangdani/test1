package com.example.test1.repository;


import com.example.test1.modele.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    public Restaurant findByNomRAndLocalisation(String nom, String localisation);
}
