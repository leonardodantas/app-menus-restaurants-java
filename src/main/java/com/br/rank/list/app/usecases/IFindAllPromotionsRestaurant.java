package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Product;

import java.util.Collection;

public interface IFindAllPromotionsRestaurant {
    Collection<Product> execute(String code);
}
