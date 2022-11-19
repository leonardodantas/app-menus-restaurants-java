package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.impl.CreateRestaurant;
import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.infra.http.converters.RestaurantConverter;
import com.br.rank.list.infra.http.jsons.requests.RestaurantRequestJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import utils.GetMockJson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateRestaurantTest {

    @InjectMocks
    private CreateRestaurant createRestaurant;
    @Mock
    private IRestaurantRepository restaurantRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var restaurantRequest = getMockJson.execute("requests/restaurant-request", RestaurantRequestJson.class);
        final var restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);

        when(this.restaurantRepository.save(any(Restaurant.class)))
                .thenReturn(restaurant);

        final var restaurantSave = createRestaurant.execute(RestaurantConverter.toDomain(restaurantRequest));
        assertThat(restaurantSave).isNotNull();
        verify(applicationEventPublisher).publishEvent(any(Restaurant.class));
    }
}
