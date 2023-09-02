package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.SearchInformation;
import com.br.rank.list.infra.database.documents.SearchInformationDocument;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchInformationConverter {

    public static SearchInformation toDomain(final SearchInformationDocument document) {
        return SearchInformation.of(document.productId(), document.nameSearch(), document.index(), document.createAt(), document.updateAt());
    }
}
