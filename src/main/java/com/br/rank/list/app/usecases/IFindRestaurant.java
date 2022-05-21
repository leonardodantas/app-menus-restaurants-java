package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Restaurant;

import java.util.Optional;

public interface IFindRestaurant {

    Optional<Restaurant> findById(String id);
    Optional<Restaurant> findByCnpj(String cnpj);
    Optional<Restaurant> findByCode(String code);
}
