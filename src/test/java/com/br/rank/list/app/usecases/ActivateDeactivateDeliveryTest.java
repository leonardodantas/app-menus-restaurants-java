package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.impl.ActivateDeactivateDelivery;
import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.infra.http.converters.DeliveryConverter;
import com.br.rank.list.infra.http.jsons.requests.DeliveryRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActivateDeactivateDeliveryTest {

    @InjectMocks
    private ActivateDeactivateDelivery activateDeactivateDelivery;

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    private final GetMockJson getMockJson = new GetMockJson();

    final String code = "123";

    private DeliveryRequest deliveryRequest;
    private Restaurant restaurant;

    @Before
    public void init() {
        this.deliveryRequest = getMockJson.execute("requests/delivery-request", DeliveryRequest.class);
        this.restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);

        when(getRestaurantOrThrowNotFound.execute(anyString()))
                .thenReturn(restaurant);

        when(restaurantRepository.save(any(Restaurant.class)))
                .thenReturn(restaurant);

    }

    @Test
    public void testActivate() {
        final var result = activateDeactivateDelivery.activate(code, DeliveryConverter.toDomain(deliveryRequest));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(restaurant.getDelivery());
    }

    @Test
    public void testDeactivate() {
        activateDeactivateDelivery.deactivate(code);
        final var restaurantDeactivate = restaurant.disableDelivery();
        verify(restaurantRepository, times(1)).save(restaurantDeactivate);
    }


}
