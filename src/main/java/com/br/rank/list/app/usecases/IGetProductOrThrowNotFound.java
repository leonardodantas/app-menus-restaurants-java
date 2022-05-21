package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Product;

public interface IGetProductOrThrowNotFound {

    Product execute(String productId);
}
