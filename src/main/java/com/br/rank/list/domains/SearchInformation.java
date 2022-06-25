package com.br.rank.list.domains;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public final class SearchInformation implements Serializable {

    private String productId;
    private String nameSearch;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private SearchInformation() {

    }

    private SearchInformation(final String productId, final String nameSearch, final LocalDateTime createAt, final LocalDateTime updateAt) {
        this.productId = productId;
        this.nameSearch = nameSearch;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static SearchInformation noSearchInformation() {
        return new SearchInformation();
    }

    public static SearchInformation of(final String productId, final String nameSearch, final LocalDateTime createAt, final LocalDateTime updateAt) {
        return new SearchInformation(productId, nameSearch, createAt, updateAt);
    }
}
