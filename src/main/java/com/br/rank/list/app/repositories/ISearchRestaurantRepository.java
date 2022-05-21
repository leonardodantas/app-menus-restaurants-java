package com.br.rank.list.app.repositories;

import com.br.rank.list.domains.SearchRestaurant;

import java.util.Collection;

public interface ISearchRestaurantRepository {

    void save(SearchRestaurant searchRestaurant);

    Collection<SearchRestaurant> findByNameContaining(String search);
}
