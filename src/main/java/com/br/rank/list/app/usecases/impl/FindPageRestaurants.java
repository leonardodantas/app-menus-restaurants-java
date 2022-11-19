package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.IFindPageRestaurants;
import com.br.rank.list.domains.Restaurant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FindPageRestaurants implements IFindPageRestaurants {

    private final IRestaurantRepository restaurantRepository;

    public FindPageRestaurants(final IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Cacheable("restaurants")
    public Page<Restaurant> execute(final int page, final int size) {
        final var activesRestaurants = restaurantRepository.findActiveRestaurants(page, size);
        final var restaurants = getSortRestaurants(activesRestaurants);
        return new PageImpl<>(restaurants, activesRestaurants.getPageable(), activesRestaurants.getTotalElements());
    }

    private List<Restaurant> getSortRestaurants(final Page<Restaurant> activesRestaurants) {
        final var openRestaurants = activesRestaurants.stream().filter(Restaurant::isOpen);
        final var closedRestaurants = activesRestaurants.stream().filter(Restaurant::isClosed);
        return Stream.concat(openRestaurants, closedRestaurants).toList();
    }
}
