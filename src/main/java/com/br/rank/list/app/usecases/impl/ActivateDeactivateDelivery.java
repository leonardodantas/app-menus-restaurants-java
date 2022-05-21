package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.IActivateDeactivateDelivery;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.Delivery;
import com.br.rank.list.domains.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class ActivateDeactivateDelivery implements IActivateDeactivateDelivery {

    private final IRestaurantRepository restaurantRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    public ActivateDeactivateDelivery(final IRestaurantRepository restaurantRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound) {
        this.restaurantRepository = restaurantRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
    }

    @Override
    public Delivery activate(final String code, final Delivery delivery) {
        final var restaurant = this.getRestaurantOrThrowNotFound.execute(code);
        final var restaurantToUpdate = Restaurant.activeDeliveryOf(restaurant, delivery);
        return restaurantRepository.save(restaurantToUpdate).getDelivery();
    }

    @Override
    public void deactivate(final String code) {
        final var restaurant = this.getRestaurantOrThrowNotFound.execute(code);
        final var restaurantToUpdate = restaurant.disableDelivery();
        restaurantRepository.save(restaurantToUpdate);
    }
}
