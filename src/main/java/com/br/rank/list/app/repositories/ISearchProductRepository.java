package com.br.rank.list.app.repositories;

import com.br.rank.list.domains.SearchProduct;

import java.util.Collection;

public interface ISearchProductRepository {

    void save(SearchProduct searchProduct);

    Collection<SearchProduct> findByCodeAndNameContaining(String code, String search);

    Collection<SearchProduct> findByNameContaining(String search);

}
