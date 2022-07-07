package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.ICreateRestaurant;
import com.br.rank.list.domains.Restaurant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreateRestaurant implements ICreateRestaurant {

    private final IRestaurantRepository restaurantRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CreateRestaurant(final IRestaurantRepository restaurantRepository, final ApplicationEventPublisher applicationEventPublisher) {
        this.restaurantRepository = restaurantRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant execute(final Restaurant restaurant) {
        final var restaurantSave = this.restaurantRepository.save(restaurant);
        this.applicationEventPublisher.publishEvent(restaurant);
        return restaurantSave;
    }

}
