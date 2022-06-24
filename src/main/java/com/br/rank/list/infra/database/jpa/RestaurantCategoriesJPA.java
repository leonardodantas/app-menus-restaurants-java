package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.domains.RestaurantCategories;
import com.br.rank.list.infra.database.documents.RestaurantCategoriesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RestaurantCategoriesJPA extends MongoRepository<RestaurantCategoriesDocument, String> {
    Optional<RestaurantCategoriesDocument> findByCode(String code);
}
