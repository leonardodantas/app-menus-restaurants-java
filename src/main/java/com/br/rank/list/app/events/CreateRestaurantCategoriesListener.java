package com.br.rank.list.app.events;

import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.RestaurantCategories;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CreateRestaurantCategoriesListener {

    private final IRestaurantCategoriesRepository restaurantCategoriesRepository;

    public CreateRestaurantCategoriesListener(final IRestaurantCategoriesRepository restaurantCategoriesRepository) {
        this.restaurantCategoriesRepository = restaurantCategoriesRepository;
    }

    @Async
    @EventListener
    public void listen(final Product product) {
        restaurantCategoriesRepository.findByCode(product.getCode())
                .ifPresentOrElse(restaurantCategories -> {
                            restaurantCategories.addCategories(product.getCategories());
                            restaurantCategoriesRepository.save(restaurantCategories);
                        },
                        () -> {
                            final var restaurantCategories = RestaurantCategories.from(product);
                            restaurantCategoriesRepository.save(restaurantCategories);
                        });
    }

}
