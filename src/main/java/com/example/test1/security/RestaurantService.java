package com.example.test1.security;

import com.example.test1.modele.DTO.RestaurantDto;
import com.example.test1.modele.Entity.Restaurant;

public interface RestaurantService {
    public Restaurant save(RestaurantDto restaurantDto);
    public Restaurant rechercherRestaurant(String nom, String localisation);

    public Restaurant findOne(Long idresto);
}