package com.br.rank.list.app.events;

import com.br.rank.list.app.repositories.ISearchRestaurantRepository;
import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.domains.SearchRestaurant;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SearchRestaurantListener {

    private final ISearchRestaurantRepository searchRestaurantRepository;

    public SearchRestaurantListener(final ISearchRestaurantRepository searchRestaurantRepository) {
        this.searchRestaurantRepository = searchRestaurantRepository;
    }

    @Async
    @EventListener
    public void listen(final Restaurant restaurant) {
        final var searchRestaurant = SearchRestaurant.from(restaurant);
        this.searchRestaurantRepository.save(searchRestaurant);
    }
}
