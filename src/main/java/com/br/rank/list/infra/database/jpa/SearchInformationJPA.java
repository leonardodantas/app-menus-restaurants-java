package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.infra.database.documents.SearchInformationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SearchInformationJPA extends MongoRepository<SearchInformationDocument, String> {
    Optional<SearchInformationDocument> findByProductId(String productId);

    void deleteByProductId(String productId);
}
