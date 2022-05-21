package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository;
import com.br.rank.list.app.usecases.IGetCategories;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.RestaurantCategories;
import org.springframework.stereotype.Service;

@Service
public class GetCategories implements IGetCategories {

    private final IRestaurantCategoriesRepository restaurantCategoriesRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    public GetCategories(final IRestaurantCategoriesRepository restaurantCategoriesRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound) {
        this.restaurantCategoriesRepository = restaurantCategoriesRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
    }

    @Override
    public Categories execute(final String code) {
        getRestaurantOrThrowNotFound.execute(code);

        return restaurantCategoriesRepository.findByCode(code)
                .map(RestaurantCategories::getCategories)
                .orElse(new Categories());
    }
}
