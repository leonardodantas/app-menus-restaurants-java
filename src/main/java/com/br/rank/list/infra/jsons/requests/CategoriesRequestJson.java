package com.br.rank.list.infra.jsons.requests;

import java.util.Collection;

public record CategoriesRequestJson(
        Collection<String> values
) {
}
