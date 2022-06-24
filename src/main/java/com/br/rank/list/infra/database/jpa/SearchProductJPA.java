package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.domains.SearchProduct;
import com.br.rank.list.infra.database.documents.SearchProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface SearchProductJPA extends MongoRepository<SearchProductDocument, String> {
    Collection<SearchProductDocument> findByCodeAndNameContainingIgnoreCase(String code, String search);
}
