package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.ISearchProductRepository;
import com.br.rank.list.app.usecases.IGetSuggestionsProductsAllRestaurants;
import com.br.rank.list.domains.SearchProduct;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GetSuggestionsProductsAllRestaurants implements IGetSuggestionsProductsAllRestaurants {

    private final ISearchProductRepository searchProductRepository;

    public GetSuggestionsProductsAllRestaurants(final ISearchProductRepository searchProductRepository) {
        this.searchProductRepository = searchProductRepository;
    }

    @Override
    public Collection<SearchProduct> execute(final String search) {
        return searchProductRepository.findByNameContaining(search);
    }
}
