package com.br.rank.list.app.usecases;

import com.br.rank.list.app.exceptions.ProductNotFoundException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.GetProductOrThrowNotFound;
import com.br.rank.list.domains.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetProductOrThrowNotFoundTest {

    @InjectMocks
    private GetProductOrThrowNotFound getProductOrThrowNotFound;

    @Mock
    private IProductRepository productRepository;

    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var productId = "123";
        final var product = getMockJson.execute("domains/product-valid", Product.class);

        when(productRepository.findById(anyString()))
                .thenReturn(Optional.of(product));

        final var result = getProductOrThrowNotFound.execute(productId);

        assertNotNull(result);
    }


    @Test(expected = ProductNotFoundException.class)
    public void testProductNotFoundException() {
        final var productId = "123";

        when(productRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        getProductOrThrowNotFound.execute(productId);
    }
}
