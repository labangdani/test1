package com.example.test1.service;

import com.example.test1.modele.DTO.RestaurantDto;
import com.example.test1.modele.Entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    public Restaurant save(RestaurantDto restaurantDto);
    public Restaurant findOne(Long idresto);
    public Restaurant findNom(String nom);

}
