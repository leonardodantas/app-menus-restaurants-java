package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.IFindRestaurant;
import com.br.rank.list.domains.Restaurant;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindRestaurant implements IFindRestaurant {

    private final IRestaurantRepository restaurantRepository;

    public FindRestaurant(final IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Optional<Restaurant> findById(final String id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Optional<Restaurant> findByCnpj(final String cnpj) {
        return restaurantRepository.findByCnpj(cnpj);
    }

    @Override
    public Optional<Restaurant> findByCode(final String code) {
        return restaurantRepository.findByCode(code);
    }
}
