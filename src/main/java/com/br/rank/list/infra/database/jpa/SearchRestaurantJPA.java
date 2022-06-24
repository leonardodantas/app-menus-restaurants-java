package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.domains.SearchRestaurant;
import com.br.rank.list.infra.database.documents.SearchRestaurantDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface SearchRestaurantJPA extends MongoRepository<SearchRestaurantDocument, String> {
    Collection<SearchRestaurantDocument> findByNameContainingIgnoreCase(String search);
}
