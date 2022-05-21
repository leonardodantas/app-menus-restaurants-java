package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Product;
import org.springframework.data.domain.Page;

public interface IFindProducts {

    Page<Product> execute(String code, int page, int size);
}
