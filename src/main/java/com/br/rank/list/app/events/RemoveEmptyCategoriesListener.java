package com.br.rank.list.app.events;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository;
import com.br.rank.list.domains.RestaurantCategories;
import com.br.rank.list.domains.RestaurantCode;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RemoveEmptyCategoriesListener {

    private final IRestaurantCategoriesRepository restaurantCategoriesRepository;
    private final IProductRepository productRepository;

    public RemoveEmptyCategoriesListener(final IRestaurantCategoriesRepository restaurantCategoriesRepository, final IProductRepository productRepository) {
        this.restaurantCategoriesRepository = restaurantCategoriesRepository;
        this.productRepository = productRepository;
    }

    @Async
    @EventListener
    public void listen(final RestaurantCode restaurantCode) {
        final var restaurantCategories = restaurantCategoriesRepository
                .findByCode(restaurantCode.code())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        final var categories = getCategoriesClean(restaurantCode, restaurantCategories);

        final var restaurantCategoriesToUpdate = restaurantCategories.from(categories);
        restaurantCategoriesRepository.save(restaurantCategoriesToUpdate);
    }

    private Set<String> getCategoriesClean(final RestaurantCode restaurantCode,
                                           final RestaurantCategories restaurantCategories) {
        final var products = productRepository.findAllByCode(restaurantCode.code());

        return restaurantCategories.getCategories().getValues()
                .stream()
                .filter(c -> products.stream().anyMatch(
                        p -> p.getCategories().getValues().contains(c)))
                .collect(Collectors.toSet());
    }
}
