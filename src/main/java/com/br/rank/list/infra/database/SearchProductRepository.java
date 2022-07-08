package com.br.rank.list.infra.database;

import com.br.rank.list.app.repositories.ISearchProductRepository;
import com.br.rank.list.domains.SearchProduct;
import com.br.rank.list.infra.database.converters.SearchProductConverter;
import com.br.rank.list.infra.database.documents.SearchProductDocument;
import com.br.rank.list.infra.database.jpa.SearchProductJPA;
import com.br.rank.list.infra.exceptions.SaveEntityException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class SearchProductRepository implements ISearchProductRepository {

    private final SearchProductJPA searchProductJPA;

    public SearchProductRepository(final SearchProductJPA searchProductJPA) {
        this.searchProductJPA = searchProductJPA;
    }

    @Override
    public void save(final SearchProduct searchProduct) {
        try {
            final var searchProductDocument = SearchProductDocument.from(searchProduct);
            searchProductJPA.save(searchProductDocument);
        } catch (Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    public Collection<SearchProduct> findByCodeAndNameContaining(String code, String search) {
        return searchProductJPA.findByCodeAndNameContainingIgnoreCase(code, search).stream().map(SearchProductConverter::toDomain).collect(Collectors.toList());
    }

    @Override
    public Collection<SearchProduct> findByNameContaining(final String search) {
        return searchProductJPA.findByNameContaining(search).stream().map(SearchProductConverter::toDomain).collect(Collectors.toList());
    }

}
