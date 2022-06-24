package com.br.rank.list.infra.database;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.Product;
import com.br.rank.list.infra.database.converters.ProductConverter;
import com.br.rank.list.infra.database.documents.ProductDocument;
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
            final var productDocument = ProductDocument.from(product);
            final var productSave = productJPA.save(productDocument);
            return ProductConverter.toDomain(productSave);
        } catch (final Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "productsInPromotion", allEntries = true)
    public Product saveProductWithPromotion(final Product product) {
        try {
            final var productDocument = ProductDocument.from(product);
            final var productSave = productJPA.save(productDocument);
            return ProductConverter.toDomain(productSave);
        } catch (final Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    public Page<Product> findAllByCode(final String code, final int page, final int size) {
        final var pageable = PageRequest.of(page, size);
        return productJPA.findAllByCode(code, pageable).map(ProductConverter::toDomain);
    }

    @Override
    public Optional<Product> findById(final String id) {
        final var product = productJPA.findById(id);
        if (product.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ProductConverter.toDomain(product.get()));
    }

    @Override
    public Collection<Product> findAllByCode(final String code) {
        final var products = productJPA.findAllByCode(code);
        return products.stream().map(ProductConverter::toDomain).toList();
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
        final var products = productJPA.findByCodeAndPromotionActiveTrue(code);
        return products.stream().map(ProductConverter::toDomain).toList();
    }

    @Override
    @Cacheable("productsInPromotion")
    public Collection<Product> findAllByCodeAndPromotionTrueCacheable(final String code) {
        final var products = productJPA.findByCodeAndPromotionActiveTrue(code);
        return products.stream().map(ProductConverter::toDomain).toList();
    }

    @Override
    public Collection<Product> findAllByCodeAndCategories(final String code, final Categories categories) {
        final var values = categories.getValues();
        final var products = productJPA.findByCodeAndCategoriesValuesIn(code, values);
        return products.stream().map(ProductConverter::toDomain).toList();
    }

}
