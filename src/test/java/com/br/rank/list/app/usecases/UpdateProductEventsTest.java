package com.br.rank.list.app.usecases;

import com.br.rank.list.app.messages.ISendProductMessage;
import com.br.rank.list.app.usecases.impl.UpdateProductEvents;
import com.br.rank.list.domains.RestaurantCode;
import com.br.rank.list.infra.http.converters.ProductConverter;
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import utils.GetMockJson;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductEventsTest {

    @InjectMocks
    private UpdateProductEvents updateProductEvents;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private ISendProductMessage sendProductMessage;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var productRequest = getMockJson.execute("requests/product-request", ProductRequestJson.class);
        final var product = ProductConverter.toDomain(productRequest);

        updateProductEvents.execute(product);

        verify(applicationEventPublisher, times(1)).publishEvent(product);
        verify(applicationEventPublisher, times(1)).publishEvent(new RestaurantCode(product.getCode()));
        verify(sendProductMessage, times(1)).execute(product);
    }
}
