package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.Product;

import java.util.Collection;

public interface IFindByCategories {
    Collection<Product> execute(String code, Categories categories);
}
