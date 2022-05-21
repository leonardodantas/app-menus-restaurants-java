package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.ISearchProductRepository;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.app.usecases.IGetSuggestionsProducts;
import com.br.rank.list.domains.SearchProduct;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GetSuggestionsProducts implements IGetSuggestionsProducts {

    private final ISearchProductRepository searchProductRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    public GetSuggestionsProducts(final ISearchProductRepository searchProductRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound) {
        this.searchProductRepository = searchProductRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
    }

    @Override
    public Collection<SearchProduct> execute(final String code, final String search) {
        getRestaurantOrThrowNotFound.execute(code);
        return searchProductRepository.findByCodeAndNameContaining(code, search);
    }
}
