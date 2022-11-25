package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.IRemoveRestaurant;
import org.springframework.stereotype.Service;

@Service
public class RemoveRestaurant implements IRemoveRestaurant {

    private final IRestaurantRepository restaurantRepository;

    public RemoveRestaurant(final IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void execute(String id) {
        restaurantRepository.findById(id)
                .ifPresentOrElse(restaurant -> {
                    restaurant.disable();
                    restaurantRepository.save(restaurant);
                }, () -> {
                    throw new RestaurantNotFoundException("Restaurant not found for removal");
                });
    }
}
