package com.br.rank.list.infra.feign;

import com.br.rank.list.app.rest.ISearchProductsAllRestaurantsRest;
import com.br.rank.list.domains.Product;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SearchProductsAllRestaurants implements ISearchProductsAllRestaurantsRest {

    private final ISearchProductsAllRestaurantsFeign searchProductsAllRestaurantsRest;

    public SearchProductsAllRestaurants(final ISearchProductsAllRestaurantsFeign searchProductsAllRestaurantsRest) {
        this.searchProductsAllRestaurantsRest = searchProductsAllRestaurantsRest;
    }

    @Override
    public Collection<Product> execute(final String q) {
        return searchProductsAllRestaurantsRest.execute(q);
    }
}
