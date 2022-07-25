package com.br.rank.list.app.usecases;

import com.br.rank.list.app.exceptions.ProductNotFoundException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.UpdateProduct;
import com.br.rank.list.domains.Product;
import com.br.rank.list.infra.http.converters.ProductConverter;
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductTest {

    @InjectMocks
    private UpdateProduct updateProduct;

    @Mock
    private IProductRepository productRepository;
    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;
    @Mock
    private IUpdateProductEvents updateProductEvents;

    @Captor
    private ArgumentCaptor<Product> argumentCaptor;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var product = getMockJson.execute("domains/product-valid", Product.class);
        final var productRequest = getMockJson.execute("requests/product-request", ProductRequestJson.class);
        final var productId = "132";

        when(productRepository.findById(anyString()))
                .thenReturn(Optional.of(product));

        when(productRepository.save(any()))
                .thenReturn(product.updateId(productId));

        updateProduct.execute(productId, ProductConverter.toDomain(productRequest));

        verify(updateProductEvents, times(1))
                .execute(argumentCaptor.capture());

        final var productUpdate = argumentCaptor.getValue();
        assertEquals(productId, productUpdate.getId());

    }

    @Test(expected = ProductNotFoundException.class)
    public void testProductNotFoundException() {
        final var productRequest = getMockJson.execute("requests/product-request", ProductRequestJson.class);
        final var productId = "132";

        when(productRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        updateProduct.execute(productId, ProductConverter.toDomain(productRequest));
    }

}
