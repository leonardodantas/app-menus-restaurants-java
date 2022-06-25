package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.IUpdateRestaurant;
import com.br.rank.list.domains.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class UpdateRestaurant implements IUpdateRestaurant {

    private final IRestaurantRepository restaurantRepository;

    public UpdateRestaurant(final IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant execute(final String id, final Restaurant restaurant) {
        final var restaurantToUpdate = restaurantRepository.findById(id)
                .orElseThrow(() -> RestaurantNotFoundException.from("Restaurant not found"));

        restaurantToUpdate.updateFrom(restaurant);

        return restaurantRepository.save(restaurantToUpdate);
    }
}
