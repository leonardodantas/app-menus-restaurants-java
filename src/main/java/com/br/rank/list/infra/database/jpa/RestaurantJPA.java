package com.br.rank.list.infra.database.jpa;

import com.br.rank.list.domains.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RestaurantJPA extends MongoRepository<Restaurant, String> {
    Page<Restaurant> findAllByActiveTrue(Pageable pageable);

    Optional<Restaurant> findByCnpjCnpjOnlyNumbersAndActiveTrue(String cnpj);

    Optional<Restaurant> findByIdAndActiveTrue(String id);

    Optional<Restaurant> findByCodeAndActiveTrue(String code);
}
