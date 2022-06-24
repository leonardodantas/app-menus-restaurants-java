package com.br.rank.list.domains;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
public class SearchInformation implements Serializable {

    private String productId;
    private String nameSearch;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
