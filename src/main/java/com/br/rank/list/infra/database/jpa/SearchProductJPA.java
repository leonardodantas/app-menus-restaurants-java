package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.domains.SearchProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface SearchProductJPA extends MongoRepository<SearchProduct, String> {
    Collection<SearchProduct> findByCodeAndNameContainingIgnoreCase(String code, String search);
}
