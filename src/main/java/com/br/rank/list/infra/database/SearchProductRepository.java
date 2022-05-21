package com.br.rank.list.infra.database;

import com.br.rank.list.app.repositories.ISearchProductRepository;
import com.br.rank.list.domains.SearchProduct;
import com.br.rank.list.infra.database.jpa.SearchProductJPA;
import com.br.rank.list.infra.exceptions.SaveEntityException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SearchProductRepository implements ISearchProductRepository {

    private final SearchProductJPA searchProductJPA;

    public SearchProductRepository(final SearchProductJPA searchProductJPA) {
        this.searchProductJPA = searchProductJPA;
    }

    @Override
    public void save(final SearchProduct searchProduct) {
        try {
            searchProductJPA.save(searchProduct);
        } catch (Exception e) {
            throw new SaveEntityException(e.getMessage());
        }
    }

    @Override
    public Collection<SearchProduct> findByCodeAndNameContaining(String code, String search) {
        return searchProductJPA.findByCodeAndNameContainingIgnoreCase(code, search);
    }
}
