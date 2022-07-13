package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.impl.ActivateDeactivateDelivery;
import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.infra.http.converters.DeliveryConverter;
import com.br.rank.list.infra.http.jsons.requests.DeliveryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivateDeactivateDeliveryTest {

    @InjectMocks
    private ActivateDeactivateDelivery activateDeactivateDelivery;

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testActivate() {
        final var code = "123";
        final var deliveryRequest = getMockJson.execute("requests/delivery-request", DeliveryRequest.class);
        final var restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);

        when(getRestaurantOrThrowNotFound.execute(anyString()))
                .thenReturn(restaurant);

        when(restaurantRepository.save(any(Restaurant.class)))
                .thenReturn(restaurant);

        final var result = activateDeactivateDelivery.activate(code, DeliveryConverter.toDomain(deliveryRequest));

        assertNotNull(result);
    }


}
