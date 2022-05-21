package com.br.rank.list.infra.database;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.Product;
import com.br.rank.list.infra.database.jpa.ProductJPA;
import com.br.rank.list.infra.exceptions.RemoveEntityException;
import com.br.rank.list.infra.exceptions.SaveEntityException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ProductRepository implements IProductRepository {

    private final ProductJPA productJPA;

    public ProductRepository(final ProductJPA productJPA) {
        this.productJPA = productJPA;
    }

    @Override
    public Product save(final Product product) {
        try {
            return productJPA.save(product);
        } catch (final Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "productsInPromotion", allEntries = true)
    public Product saveProductWithPromotion(final Product product) {
        try {
            return productJPA.save(product);
        } catch (final Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    public Page<Product> findAllByCode(final String code, final int page, final int size) {
        final var pageable = PageRequest.of(page, size);
        return productJPA.findAllByCode(code, pageable);
    }

    @Override
    public Optional<Product> findById(final String id) {
        return productJPA.findById(id);
    }

    @Override
    public Collection<Product> findAllByCode(final String code) {
        return productJPA.findAllByCode(code);
    }

    @Override
    public void removeById(final String id) {
        try {
            productJPA.deleteById(id);
        } catch (final Exception e) {
            throw new RemoveEntityException(e.getMessage());
        }
    }

    @Override
    public Collection<Product> findByCodeAndPromotionTrue(final String code) {
        return productJPA.findByCodeAndPromotionActiveTrue(code);
    }

    @Override
    @Cacheable("productsInPromotion")
    public Collection<Product> findAllByCodeAndPromotionTrueCacheable(final String code) {
        return productJPA.findByCodeAndPromotionActiveTrue(code);
    }

    @Override
    public Collection<Product> findAllByCodeAndCategories(final String code, final Categories categories) {
        final var values = categories.getValues();
        return productJPA.findByCodeAndCategoriesValuesIn(code, values);
    }

}
