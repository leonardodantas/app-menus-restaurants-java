package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.Promotion;

public interface ICreatePromotion {
    Product execute(String productId, Promotion promotion);
}
