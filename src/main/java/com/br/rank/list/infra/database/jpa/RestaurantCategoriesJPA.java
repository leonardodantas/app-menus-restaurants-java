package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.domains.RestaurantCategories;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RestaurantCategoriesJPA extends MongoRepository<RestaurantCategories, String> {
    Optional<RestaurantCategories> findByCode(String code);
}
