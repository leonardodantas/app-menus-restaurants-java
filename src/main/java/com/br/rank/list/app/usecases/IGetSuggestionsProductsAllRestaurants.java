package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.SearchProduct;

import java.util.Collection;

public interface IGetSuggestionsProductsAllRestaurants {
    Collection<SearchProduct> execute(String search);
}
