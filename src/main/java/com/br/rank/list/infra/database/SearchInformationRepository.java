package com.br.rank.list.infra.database;

import com.br.rank.list.app.repositories.ISearchInformationRepository;
import com.br.rank.list.domains.SearchInformation;
import com.br.rank.list.infra.database.converters.SearchInformationConverter;
import com.br.rank.list.infra.database.documents.SearchInformationDocument;
import com.br.rank.list.infra.database.jpa.SearchInformationJPA;
import com.br.rank.list.infra.exceptions.RemoveEntityException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SearchInformationRepository implements ISearchInformationRepository {

    private final SearchInformationJPA searchInformationJPA;

    public SearchInformationRepository(final SearchInformationJPA searchInformationJPA) {
        this.searchInformationJPA = searchInformationJPA;
    }

    @Override
    public Optional<SearchInformation> findByProductId(final String productId) {
        final var document = searchInformationJPA.findByProductId(productId);
        return document.map(SearchInformationConverter::toDomain);
    }

    @Override
    public void save(final SearchInformation searchInformation) {
        try {
            searchInformationJPA.save(SearchInformationDocument.from(searchInformation));
        } catch (Exception e) {
            throw new RemoveEntityException(e.getMessage());
        }
    }

    @Override
    public void removeByProductId(String productId) {
        try {
            searchInformationJPA.deleteByProductId(productId);
        } catch (Exception e) {
            throw new RemoveEntityException(e.getMessage());
        }
    }
}
