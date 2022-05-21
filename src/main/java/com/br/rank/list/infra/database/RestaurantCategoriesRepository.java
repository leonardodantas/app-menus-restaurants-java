package com.br.rank.list.infra.database;

import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository;
import com.br.rank.list.domains.RestaurantCategories;
import com.br.rank.list.infra.database.jpa.RestaurantCategoriesJPA;
import com.br.rank.list.infra.exceptions.SaveEntityException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RestaurantCategoriesRepository implements IRestaurantCategoriesRepository {

    private final RestaurantCategoriesJPA restaurantCategoriesJPA;

    public RestaurantCategoriesRepository(final RestaurantCategoriesJPA restaurantCategoriesJPA) {
        this.restaurantCategoriesJPA = restaurantCategoriesJPA;
    }

    @Override
    public void save(final RestaurantCategories restaurantCategories) {
        try {
            restaurantCategoriesJPA.save(restaurantCategories);
        } catch (final Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    public Optional<RestaurantCategories> findByCode(final String code) {
        return restaurantCategoriesJPA.findByCode(code);
    }
}
