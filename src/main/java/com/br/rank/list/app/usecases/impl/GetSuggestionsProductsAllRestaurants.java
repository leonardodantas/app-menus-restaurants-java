package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.rest.ISearchProductsAllRestaurantsRest;
import com.br.rank.list.app.usecases.IGetSuggestionsProductsAllRestaurants;
import com.br.rank.list.domains.SearchProduct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class GetSuggestionsProductsAllRestaurants implements IGetSuggestionsProductsAllRestaurants {

    private final ISearchProductsAllRestaurantsRest searchProductsAllRestaurantsRest;

    public GetSuggestionsProductsAllRestaurants(
            final ISearchProductsAllRestaurantsRest searchProductsAllRestaurantsRest) {
        this.searchProductsAllRestaurantsRest = searchProductsAllRestaurantsRest;
    }

    @Override
    public Collection<SearchProduct> execute(final String search) {
        return searchProductsAllRestaurantsRest.execute(search).stream().map(SearchProduct::from).toList();
    }
}
