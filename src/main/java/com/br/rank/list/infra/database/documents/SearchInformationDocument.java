package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.SearchInformation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("searchInformation")
public record SearchInformationDocument(

        @Id
        String productId,
        String nameSearch,
        String index,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {

    public static SearchInformationDocument from(final SearchInformation searchInformation) {
        return new SearchInformationDocument(searchInformation.getProductId(), searchInformation.getNameSearch(), searchInformation.getIndex(), searchInformation.getCreateAt(), searchInformation.getUpdateAt());
    }

}
