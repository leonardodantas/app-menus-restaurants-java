package com.br.rank.list.infra.database;

import com.br.rank.list.app.repositories.ISearchRestaurantRepository;
import com.br.rank.list.domains.SearchRestaurant;
import com.br.rank.list.infra.database.jpa.SearchRestaurantJPA;
import com.br.rank.list.infra.exceptions.SaveEntityException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SearchRestaurantRepository implements ISearchRestaurantRepository {

    private final SearchRestaurantJPA searchRestaurantJPA;

    public SearchRestaurantRepository(final SearchRestaurantJPA searchRestaurantJPA) {
        this.searchRestaurantJPA = searchRestaurantJPA;
    }

    @Override
    public void save(final SearchRestaurant searchRestaurant) {
        try {
            this.searchRestaurantJPA.save(searchRestaurant);
        } catch (final Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    public Collection<SearchRestaurant> findByNameContaining(final String search) {
        return searchRestaurantJPA.findByNameContainingIgnoreCase(search);
    }
}
