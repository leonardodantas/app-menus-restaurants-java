package com.br.rank.list.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Getter
@NoArgsConstructor
public class Categories implements Serializable {
    private Collection<String> values;

    private Categories(final Collection<String> values) {
        this.values = values;
    }

    public static Categories from(final Collection<String> categories) {
        final var values = categories.stream().map(String::toUpperCase).toList();
        return new Categories(values);
    }

}
