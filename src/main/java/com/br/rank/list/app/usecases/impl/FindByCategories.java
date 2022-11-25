package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository;
import com.br.rank.list.app.usecases.IFindByCategories;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.RestaurantCategories;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FindByCategories implements IFindByCategories {

    private final IRestaurantCategoriesRepository restaurantCategoriesRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;
    private final IProductRepository productRepository;

    public FindByCategories(final IRestaurantCategoriesRepository restaurantCategoriesRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound, final IProductRepository productRepository) {
        this.restaurantCategoriesRepository = restaurantCategoriesRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> execute(final String code, final Categories categories) {
        getRestaurantOrThrowNotFound.execute(code);

        final var restaurantCategories = restaurantCategoriesRepository.findByCode(code)
                .orElseThrow(() -> new RestaurantNotFoundException(String.format("Restaurant code %s not found", code)));

        validateCategories(categories, restaurantCategories);
        return productRepository.findAllByCodeAndCategories(code, categories);
    }

    private void validateCategories(final Categories categories, final RestaurantCategories restaurantCategories) {
        final var validCategories = categories
                .getValues()
                .stream()
                .allMatch(value -> restaurantCategories.getValues().stream().anyMatch(rC -> rC.equals(value)));

        if (!validCategories) {
            throw new IllegalArgumentException("Categories do not belong to the restaurant");
        }
    }
}
