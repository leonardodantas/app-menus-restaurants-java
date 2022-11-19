package com.br.rank.list.app.usecases;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.impl.UpdateRestaurant;
import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.infra.http.converters.RestaurantConverter;
import com.br.rank.list.infra.http.jsons.requests.RestaurantRequestJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateRestaurantTest {

    @InjectMocks
    private UpdateRestaurant updateRestaurant;

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Captor
    private ArgumentCaptor<Restaurant> argumentCaptor;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var restaurantRequest = getMockJson.execute("requests/restaurant-request", RestaurantRequestJson.class);
        final var restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);

        final var restaurantUpdate = getMockJson.execute("domains/restaurant-valid", Restaurant.class);
        restaurantUpdate.updateFrom(RestaurantConverter.toDomain(restaurantRequest));

        final var restaurantId = "123";

        when(restaurantRepository.findById(anyString()))
                .thenReturn(Optional.of(restaurant));

        when(restaurantRepository.save(any()))
                .thenReturn(restaurantUpdate);

        final var result = updateRestaurant.execute(restaurantId, RestaurantConverter.toDomain(restaurantRequest));

        verify(restaurantRepository, times(1)).save(argumentCaptor.capture());

        final var restaurantUpdateCapture = argumentCaptor.getValue();
        assertThat(result).isNotNull();
        assertThat(restaurantUpdate.getAddress().getStreet()).isEqualTo(restaurantUpdateCapture.getAddress().getStreet());
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testRestaurantNotFoundException() {
        final var restaurantRequest = getMockJson.execute("requests/restaurant-request", RestaurantRequestJson.class);

        final var restaurantId = "123";

        when(restaurantRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        final var result = updateRestaurant.execute(restaurantId, RestaurantConverter.toDomain(restaurantRequest));

        assertThat(result).isNotNull();
    }
}
