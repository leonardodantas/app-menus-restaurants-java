package com.br.rank.list.domains;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public final class SearchInformation implements Serializable {

    private String productId;
    private String nameSearch;
    private String index;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private SearchInformation() {

    }

    private SearchInformation(final SearchInformation searchInformationActual, final SearchInformation searchInformationUpdate) {
        this.productId = searchInformationActual.getProductId();
        this.createAt = searchInformationActual.getCreateAt();
        this.nameSearch = searchInformationUpdate.getNameSearch();
        this.index = searchInformationUpdate.getIndex();
        this.updateAt = searchInformationUpdate.getUpdateAt();
    }

    private SearchInformation(final SearchInformation searchInformation) {
        this.productId = searchInformation.getProductId();
        this.createAt = searchInformation.getCreateAt();
        this.nameSearch = searchInformation.getNameSearch();
        this.index = searchInformation.getIndex();
        this.updateAt = searchInformation.getUpdateAt();
    }

    public SearchInformation(final String productId, final String nameSearch, final String index, final LocalDateTime createAt, final LocalDateTime updateAt) {
        this.productId = productId;
        this.nameSearch = nameSearch;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.index = index;
    }

    public static SearchInformation of(final String productId, final String nameSearch, final String index, final LocalDateTime createAt, final LocalDateTime updateAt) {
        return new SearchInformation(productId, nameSearch, index, createAt, updateAt);
    }

    public static SearchInformation from(final SearchInformation searchInformation) {
        return new SearchInformation(searchInformation);
    }

    public SearchInformation updateFrom(final SearchInformation searchInformation) {
        return new SearchInformation(this, searchInformation);
    }
}
