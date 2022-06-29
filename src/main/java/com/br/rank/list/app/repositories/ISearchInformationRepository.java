package com.br.rank.list.app.repositories;

import com.br.rank.list.domains.SearchInformation;

import java.util.Optional;

public interface ISearchInformationRepository {

    Optional<SearchInformation> findByProductId(String productId);

    void save(SearchInformation searchInformation);
}
