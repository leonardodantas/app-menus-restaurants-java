package com.br.rank.list.app.repositories;

import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.Product;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface IProductRepository {

    Product save(Product product);

    Product saveProductWithPromotion(Product product);

    Page<Product> findAllByCode(String code, int page, int size);

    Optional<Product> findById(String id);

    Collection<Product> findAllByCode(String code);

    void removeById(String id);

    Collection<Product> findByCodeAndPromotionTrue(String code);

    Collection<Product> findAllByCodeAndPromotionTrueCacheable(String code);

    Collection<Product> findAllByCodeAndCategories(String code, Categories categories);
}
