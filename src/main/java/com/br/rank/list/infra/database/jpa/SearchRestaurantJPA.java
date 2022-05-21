package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.domains.SearchRestaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface SearchRestaurantJPA extends MongoRepository<SearchRestaurant, String> {
    Collection<SearchRestaurant> findByNameContainingIgnoreCase(String search);
}
