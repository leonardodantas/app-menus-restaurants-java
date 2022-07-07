package com.br.rank.list.infra.http.jsons.requests;

import java.util.Collection;

public record CategoriesRequestJson(
        Collection<String> values
) {
}
