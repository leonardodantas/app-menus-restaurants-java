package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IFindProducts;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FindPageProducts implements IFindProducts {

    private final IProductRepository productRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    public FindPageProducts(final IProductRepository productRepository, IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound) {
        this.productRepository = productRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
    }

    @Override
    @Cacheable("products")
    public Page<Product> execute(final String code, final int page, final int size) {
        getRestaurantOrThrowNotFound.execute(code);
        final var productsPage = productRepository.findAllByCode(code, page, size);
        return new PageImpl<Product>(productsPage.getContent(), PageRequest.of(page, size), productsPage.getTotalElements());
    }

}
