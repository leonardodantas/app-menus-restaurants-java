package com.br.rank.list.domains;

import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
public final class Categories implements Serializable {
    private Collection<String> values;

    private Categories(final Collection<String> values) {
        this.values = values;
    }

    private Categories() {
        this.values = List.of();
    }

    public static Categories from(final Collection<String> categories) {
        final var values = categories.stream().map(String::toUpperCase).toList();
        return new Categories(values);
    }

    public static Categories empty() {
        return new Categories();
    }

}
