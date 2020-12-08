package com.example.test1.security;


import com.example.test1.modele.DTO.RestaurantDto;
import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IRestaurantService implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant save(RestaurantDto restaurantDto) {
            Restaurant restaurant = new Restaurant();
            restaurant.setNomR(restaurantDto.getNomR());
            restaurant.setIdresto(restaurantDto.getIdresto());
            restaurant.setLocalisation(restaurantDto.getLocalisation());
            restaurant.setImage(restaurantDto.getImage());
            restaurant.setDescription(restaurantDto.getDescription());
            restaurant.setMailR(restaurantDto.getMailR());
            restaurant.setTel(restaurantDto.getTel());
            restaurant.setLundi(restaurantDto.getLundi());
            restaurant.setMardi(restaurantDto.getMardi());
            restaurant.setMercredi(restaurantDto.getMercredi());
            restaurant.setJeudi(restaurantDto.getJeudi());
            restaurant.setVendredi(restaurantDto.getVendredi());
            restaurant.setSamedi(restaurantDto.getSamedi());
            restaurant.setDimanche(restaurantDto.getDimanche());
            restaurant.setType(restaurantDto.getType());

        return restaurantRepository.save(restaurant);
        }


    @Override
    public Restaurant rechercherRestaurant(String nom, String localisation) {
        return restaurantRepository.findByNomRAndLocalisation(nom, localisation);
    }

    @Override
    public Restaurant findOne(Long idresto) {
        return restaurantRepository.findByIdresto(idresto);
    }
}
