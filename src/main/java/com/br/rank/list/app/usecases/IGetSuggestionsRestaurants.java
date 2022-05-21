package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.SearchRestaurant;

import java.util.Collection;

public interface IGetSuggestionsRestaurants {

    Collection<SearchRestaurant> execute(String search);
}
