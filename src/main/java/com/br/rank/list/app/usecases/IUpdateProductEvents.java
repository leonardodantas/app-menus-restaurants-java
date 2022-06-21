package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Product;

public interface IUpdateProductEvents {

    void execute(Product product);
}
