package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.ProductNotFoundException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IUpdateSearchInformation;
import com.br.rank.list.domains.SearchInformation;
import org.springframework.stereotype.Service;

@Service
public class UpdateSearchInformation implements IUpdateSearchInformation {

    private final IProductRepository productRepository;

    public UpdateSearchInformation(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void execute(final SearchInformation searchInformation) {
        final var product = productRepository.findById(searchInformation.getProductId())
                .orElseThrow(() -> ProductNotFoundException.from(String.format("Product %s not found", searchInformation.getProductId())));

        final var productToUpdate = product.updateSearch(searchInformation);

        productRepository.save(productToUpdate);
    }
}
