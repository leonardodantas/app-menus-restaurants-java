package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.repositories.ISearchInformationRepository;
import com.br.rank.list.app.usecases.IUpdateSearchInformation;
import com.br.rank.list.domains.SearchInformation;
import org.springframework.stereotype.Service;

@Service
public class UpdateSearchInformation implements IUpdateSearchInformation {

    private final IProductRepository productRepository;
    private final ISearchInformationRepository searchInformationRepository;

    public UpdateSearchInformation(final IProductRepository productRepository, final ISearchInformationRepository searchInformationRepository) {
        this.productRepository = productRepository;
        this.searchInformationRepository = searchInformationRepository;
    }

    @Override
    public void execute(final SearchInformation searchInformation) {
        final var searchInformationToSave = searchInformationRepository.findByProductId(searchInformation.getProductId())
                .map(search -> search.updateFrom(searchInformation)).orElseGet(() -> SearchInformation.from(searchInformation));

        searchInformationRepository.save(searchInformationToSave);
    }
}
