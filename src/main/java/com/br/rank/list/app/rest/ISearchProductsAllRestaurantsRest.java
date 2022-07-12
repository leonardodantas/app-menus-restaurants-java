package com.br.rank.list.app.rest;

import com.br.rank.list.domains.Product;

import java.util.Collection;

public interface ISearchProductsAllRestaurantsRest {

    Collection<Product> execute(String q);
}
