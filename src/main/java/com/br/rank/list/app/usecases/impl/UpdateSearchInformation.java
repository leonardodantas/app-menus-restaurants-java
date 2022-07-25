package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.ISearchInformationRepository;
import com.br.rank.list.app.usecases.IUpdateSearchInformation;
import com.br.rank.list.domains.SearchInformation;
import org.springframework.stereotype.Service;

@Service
public class UpdateSearchInformation implements IUpdateSearchInformation {

    private final ISearchInformationRepository searchInformationRepository;

    public UpdateSearchInformation(final ISearchInformationRepository searchInformationRepository) {
        this.searchInformationRepository = searchInformationRepository;
    }

    @Override
    public void execute(final SearchInformation searchInformation) {
        searchInformationRepository.findByProductId(searchInformation.getProductId())
                .ifPresentOrElse(search -> {
                    final var searchInformationToSave = search.updateFrom(searchInformation);
                    searchInformationRepository.save(searchInformationToSave);
                }, () -> {
                    final var searchInformationToSave = SearchInformation.from(searchInformation);
                    searchInformationRepository.save(searchInformationToSave);
                });

    }
}
