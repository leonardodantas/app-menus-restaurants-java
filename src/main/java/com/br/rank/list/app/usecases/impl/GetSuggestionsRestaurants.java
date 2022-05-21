package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.ISearchRestaurantRepository;
import com.br.rank.list.app.usecases.IGetSuggestionsRestaurants;
import com.br.rank.list.domains.SearchRestaurant;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GetSuggestionsRestaurants implements IGetSuggestionsRestaurants {

    private final ISearchRestaurantRepository searchRestaurantRepository;

    public GetSuggestionsRestaurants(final ISearchRestaurantRepository searchRestaurantRepository) {
        this.searchRestaurantRepository = searchRestaurantRepository;
    }

    @Override
    public Collection<SearchRestaurant> execute(final String search) {
        return searchRestaurantRepository.findByNameContaining(search);
    }
}
