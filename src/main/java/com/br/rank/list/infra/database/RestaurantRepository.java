package com.br.rank.list.infra.database;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.infra.database.jpa.RestaurantJPA;
import com.br.rank.list.infra.exceptions.SaveEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RestaurantRepository implements IRestaurantRepository {

    private final RestaurantJPA restaurantJPA;

    public RestaurantRepository(final RestaurantJPA restaurantJPA) {
        this.restaurantJPA = restaurantJPA;
    }

    @Override
    public Restaurant save(final Restaurant restaurant) {
        try {
            return this.restaurantJPA.save(restaurant);
        } catch (final Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    public Page<Restaurant> findActiveRestaurants(final int page, final int size) {
        final var pageable = PageRequest.of(page, size);
        return restaurantJPA.findAllByActiveTrue(pageable);
    }

    @Override
    public Optional<Restaurant> findById(final String id) {
        return restaurantJPA.findByIdAndActiveTrue(id);
    }

    @Override
    public Optional<Restaurant> findByCnpj(final String cnpj) {
        return restaurantJPA.findByCnpjCnpjOnlyNumbersAndActiveTrue(cnpj);
    }

    @Override
    public Optional<Restaurant> findByCode(final String code) {
        return restaurantJPA.findByCodeAndActiveTrue(code);
    }
}
