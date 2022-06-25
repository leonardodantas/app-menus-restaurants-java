package com.br.rank.list.domains;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public final class RestaurantCategories {

    @Id
    private String id;
    @Indexed(unique = true)
    private String code;
    private Categories categories;

    private RestaurantCategories(final Product product) {
        this.code = product.getCode();
        this.categories = product.getCategories();
    }

    private RestaurantCategories(final String id, final String code, final Categories categories) {
        this.id = id;
        this.code = code;
        this.categories = categories;
    }

    public static RestaurantCategories from(final Product product) {
        return new RestaurantCategories(product);
    }

    public static RestaurantCategories of(final String id, final String code, final Categories categories) {
        return new RestaurantCategories(id, code, categories);
    }

    public void addCategories(final Categories newCategories) {
        final var allCategories = new HashSet<>(this.categories
                .getValues());

        allCategories.addAll(newCategories.getValues());

        this.categories = Categories.from(allCategories);
    }

    public RestaurantCategories from(final Set<String> categories) {
        return new RestaurantCategories(id, code, Categories.from(categories));
    }

    public Collection<String> getValues() {
        return categories.getValues();
    }
}
