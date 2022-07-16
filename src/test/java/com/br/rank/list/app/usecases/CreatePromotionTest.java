package com.br.rank.list.app.usecases;

import com.br.rank.list.app.exceptions.PromotionAlreadyExistException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.CreatePromotion;
import com.br.rank.list.domains.Product;
import com.br.rank.list.infra.http.converters.PromotionConverter;
import com.br.rank.list.infra.http.jsons.requests.PromotionRequestJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreatePromotionTest {

    @InjectMocks
    public CreatePromotion createPromotion;

    @Mock
    private IProductRepository productRepository;

    @Mock
    private IGetProductOrThrowNotFound getProductOrThrowNotFound;

    @Captor
    private ArgumentCaptor<Product> argumentCaptor;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var productId = "123";
        final var promotionRequest = getMockJson.execute("requests/promotion-request", PromotionRequestJson.class);
        final var product = getMockJson.execute("domains/product-valid", Product.class);

        when(getProductOrThrowNotFound.execute(anyString()))
                .thenReturn(product);

        when(productRepository.saveProductWithPromotion(any(Product.class)))
                .thenReturn(product);

        final var productSave = createPromotion.execute(productId, PromotionConverter.toDomain(promotionRequest));

        verify(productRepository, times(1)).saveProductWithPromotion(argumentCaptor.capture());

        final var productExcepted = argumentCaptor.getValue();

        assertNotNull(productSave);
        assertTrue(productExcepted.isPromotionActive());
    }

    @Test(expected = PromotionAlreadyExistException.class)
    public void testExecutePromotionAlreadyExists() {
        final var productId = "123";
        final var promotionRequest = getMockJson.execute("requests/promotion-request", PromotionRequestJson.class);
        final var product = getMockJson.execute("domains/product-with-promotion-valid", Product.class);

        when(getProductOrThrowNotFound.execute(anyString()))
                .thenReturn(product);

        createPromotion.execute(productId, PromotionConverter.toDomain(promotionRequest));

    }

}
