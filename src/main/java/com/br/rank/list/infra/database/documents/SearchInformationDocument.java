package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.SearchInformation;

import java.time.LocalDateTime;

public record SearchInformationDocument(
        String productId,
        String nameSearch,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {

    public static SearchInformationDocument from(final SearchInformation searchInformation) {
        return new SearchInformationDocument(searchInformation.getProductId(), searchInformation.getNameSearch(), searchInformation.getCreateAt(), searchInformation.getUpdateAt());
    }

}
