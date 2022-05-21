package com.br.rank.list.app.repositories;

import com.br.rank.list.domains.RestaurantCategories;

import java.util.Optional;

public interface IRestaurantCategoriesRepository {

    void save(RestaurantCategories restaurantCategories);
    Optional<RestaurantCategories> findByCode(String code);
}
