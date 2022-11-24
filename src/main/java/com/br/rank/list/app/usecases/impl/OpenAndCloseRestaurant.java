package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.IOpenAndCloseRestaurant;
import com.br.rank.list.domains.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class OpenAndCloseRestaurant implements IOpenAndCloseRestaurant {

    private final IRestaurantRepository restaurantRepository;

    public OpenAndCloseRestaurant(final IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant execute(final String id, final OpenCloseRestaurant openCloseRestaurant) {
        return restaurantRepository.findById(id).map(restaurant -> {
            final var restaurantUpdated = openCloseRestaurant.execute(restaurant);
            return restaurantRepository.save(restaurantUpdated);
        }).orElseThrow(() -> RestaurantNotFoundException.from("Restaurant not found"));
    }
}
