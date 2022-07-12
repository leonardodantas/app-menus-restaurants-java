package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.infra.database.documents.ProductDocument;
import com.br.rank.list.infra.database.documents.SearchProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface ProductJPA extends MongoRepository<ProductDocument, String> {
    Page<ProductDocument> findAllByCode(String code, Pageable pageable);

    Collection<ProductDocument> findAllByCode(String code);

    Collection<ProductDocument> findByCodeAndPromotionActiveTrue(String code);

    Collection<ProductDocument> findByCodeAndCategoriesValuesIn(String code, Collection<String> categories);

    Collection<ProductDocument> findByNameContainingIgnoreCase(String search);
}
