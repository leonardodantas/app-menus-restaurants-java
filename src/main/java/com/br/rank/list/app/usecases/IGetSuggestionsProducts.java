package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.SearchProduct;

import java.util.Collection;

public interface IGetSuggestionsProducts {
    Collection<SearchProduct> execute(String code, String search);
}
