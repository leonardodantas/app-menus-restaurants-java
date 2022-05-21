package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Product;

public interface IUpdateProduct {
    void execute(String id, Product request);
}
