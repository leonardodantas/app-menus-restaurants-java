package com.br.rank.list.app.events;

import com.br.rank.list.app.repositories.ISearchProductRepository;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.SearchProduct;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SearchProductListener {

    private final ISearchProductRepository searchProductRepository;

    public SearchProductListener(final ISearchProductRepository searchProductRepository) {
        this.searchProductRepository = searchProductRepository;
    }

    @Async
    @EventListener
    public void listen(final Product product) {
        final var searchProduct = SearchProduct.from(product);
        this.searchProductRepository.save(searchProduct);
    }
}
