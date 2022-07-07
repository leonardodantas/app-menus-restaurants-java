package com.br.rank.list.infra.http.jsons.responses;

import java.util.Collection;

public record PageResponse<T>(
        Collection<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
