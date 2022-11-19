package com.br.rank.list.app.usecases;

import com.br.rank.list.app.exceptions.PromotionNotExistException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.RemovePromotionDay;
import com.br.rank.list.domains.Days;
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
public class RemovePromotionDayTest {

    @InjectMocks
    private RemovePromotionDay removePromotionDay;
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

        when(getProductOrThrowNotFound.execute(anyString()))
                .thenReturn(product);

        final var productId = "321";
        removePromotionDay.execute(productId, Days.MONDAY);

        verify(productRepository, times(1)).save(argumentCaptor.capture());

        final var productUpdate = argumentCaptor.getValue();
        assertThat(productUpdate.isPromotionActive()).isFalse();
    }

    @Test(expected = PromotionNotExistException.class)
    public void testPromotionNotExistException() {
        final var product = getMockJson.execute("domains/product-with-promotion-valid", Product.class);

        when(getProductOrThrowNotFound.execute(anyString()))
                .thenReturn(product);

        final var productId = "321";
        removePromotionDay.execute(productId, Days.FRIDAY);
    }
}
