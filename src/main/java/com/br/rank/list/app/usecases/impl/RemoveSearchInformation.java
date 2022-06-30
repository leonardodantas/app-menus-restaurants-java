package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.ISearchInformationRepository;
import com.br.rank.list.app.usecases.IRemoveSearchInformation;
import org.springframework.stereotype.Service;

@Service
public class RemoveSearchInformation implements IRemoveSearchInformation {

    private final ISearchInformationRepository searchInformationRepository;

    public RemoveSearchInformation(final ISearchInformationRepository searchInformationRepository) {
        this.searchInformationRepository = searchInformationRepository;
    }

    @Override
    public void execute(final String productId) {
        searchInformationRepository.removeByProductId(productId);
    }
}
