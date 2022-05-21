package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.SearchProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface ProductJPA extends MongoRepository<Product, String> {
    Page<Product> findAllByCode(String code, Pageable pageable);

    Collection<Product> findAllByCode(String code);

    Collection<Product> findByCodeAndPromotionActiveTrue(String code);

    Collection<Product> findByCodeAndCategoriesValuesIn(String code, Collection<String> categories);

    Collection<SearchProduct> findByCodeAndNameContainingIgnoreCase(String code, String search);
}
