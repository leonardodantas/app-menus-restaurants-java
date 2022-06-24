package com.br.rank.list.infra.database;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.infra.database.converters.RestaurantConverter;
import com.br.rank.list.infra.database.documents.RestaurantDocument;
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
            final var restaurantDocument = RestaurantDocument.from(restaurant);
            final var restaurantSave = restaurantJPA.save(restaurantDocument);
            return RestaurantConverter.toDomain(restaurantSave);
        } catch (final Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    public Page<Restaurant> findActiveRestaurants(final int page, final int size) {
        final var pageable = PageRequest.of(page, size);
        return restaurantJPA.findAllByActiveTrue(pageable).map(RestaurantConverter::toDomain);
    }

    @Override
    public Optional<Restaurant> findById(final String id) {
        final var restaurant = restaurantJPA.findByIdAndActiveTrue(id);
        if (restaurant.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(RestaurantConverter.toDomain(restaurant.get()));
    }

    @Override
    public Optional<Restaurant> findByCnpj(final String cnpj) {
        final var restaurant = restaurantJPA.findByCnpjCnpjOnlyNumbersAndActiveTrue(cnpj);
        if (restaurant.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(RestaurantConverter.toDomain(restaurant.get()));
    }

    @Override
    public Optional<Restaurant> findByCode(final String code) {
        final var restaurant = restaurantJPA.findByCodeAndActiveTrue(code);
        if (restaurant.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(RestaurantConverter.toDomain(restaurant.get()));
    }
}
