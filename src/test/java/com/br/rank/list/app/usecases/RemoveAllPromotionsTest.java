package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.RemoveAllPromotions;
import com.br.rank.list.domains.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveAllPromotionsTest {

    @InjectMocks
    private RemoveAllPromotions removeAllPromotions;
    @Mock
    private IProductRepository productRepository;
    @Mock
    private IGetProductOrThrowNotFound getProductOrThrowNotFound;

    @Captor
    private ArgumentCaptor<Product> argumentCaptor;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var product = getMockJson.execute("domains/product-with-promotion-valid", Product.class);
        when(getProductOrThrowNotFound.execute(anyString())).thenReturn(product);

        final var productId = "123456";

        removeAllPromotions.execute(productId);

        verify(productRepository, times(1))
                .save(argumentCaptor.capture());

        final var productSave = argumentCaptor.getValue();
        assertThat(productSave.isPromotionActive()).isFalse();
    }

}
