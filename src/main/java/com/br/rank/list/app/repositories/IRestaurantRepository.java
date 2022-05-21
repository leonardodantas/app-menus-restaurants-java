package com.br.rank.list.app.repositories;

import com.br.rank.list.domains.Restaurant;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IRestaurantRepository {

    Restaurant save(Restaurant restaurant);

    Page<Restaurant> findActiveRestaurants(int page, int size);

    Optional<Restaurant> findById(String id);

    Optional<Restaurant> findByCnpj(String cnpj);

    Optional<Restaurant> findByCode(String code);
}
