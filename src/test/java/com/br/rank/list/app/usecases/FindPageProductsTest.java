package com.br.rank.list.app.usecases;


import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.FindPageProducts;
import com.br.rank.list.domains.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import utils.GetMockJson;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindPageProductsTest {

    @InjectMocks
    private FindPageProducts findPageProducts;
    @Mock
    private IProductRepository productRepository;
    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var type = new TypeReference<Collection<Product>>() {};

        final var products = getMockJson.execute("domains/products-valid", type);
        final var productPage = new PageImpl<>(products.stream().toList());

        when(productRepository.findAllByCode(anyString(), anyInt(), anyInt()))
                .thenReturn(productPage);

        final var code = "123";
        final var page = 0;
        final var size = 20;

        final var result = findPageProducts.execute(code, page, size);

        assertNotNull(result);
        assertTrue(result.getTotalElements() > 0);

    }

}
