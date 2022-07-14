package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.CreateProduct;
import com.br.rank.list.domains.Product;
import com.br.rank.list.infra.http.converters.ProductConverter;
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateProductTest {

    @InjectMocks
    private CreateProduct createProduct;

    @Mock
    private IProductRepository productRepository;

    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    @Mock
    private ICreateProductEvents createProductEvents;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute(){
        final var productRequest = getMockJson.execute("requests/product-request", ProductRequestJson.class);
        final var product = getMockJson.execute("domains/product-valid", Product.class);

        when(productRepository.save(any(Product.class)))
                .thenReturn(product);

        final var productSave = createProduct.execute(ProductConverter.toDomain(productRequest));

        verify(createProductEvents, times(1)).execute(product);
        assertNotNull(productSave);

    }
}
