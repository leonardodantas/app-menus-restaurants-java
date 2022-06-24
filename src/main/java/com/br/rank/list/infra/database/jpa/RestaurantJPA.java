package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.infra.database.documents.RestaurantDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RestaurantJPA extends MongoRepository<RestaurantDocument, String> {
    Page<RestaurantDocument> findAllByActiveTrue(Pageable pageable);

    Optional<RestaurantDocument> findByCnpjCnpjOnlyNumbersAndActiveTrue(String cnpj);

    Optional<RestaurantDocument> findByIdAndActiveTrue(String id);

    Optional<RestaurantDocument> findByCodeAndActiveTrue(String code);
}
