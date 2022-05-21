package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class GetRestaurantOrThrowNotFound implements IGetRestaurantOrThrowNotFound {

    private final IRestaurantRepository restaurantRepository;

    public GetRestaurantOrThrowNotFound(final IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant execute(final String code) {
        return restaurantRepository.findByCode(code)
                .orElseThrow(() -> RestaurantNotFoundException.from("Restaurant not found"));
    }
}
