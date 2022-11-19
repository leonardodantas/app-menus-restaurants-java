package com.br.rank.list.app.usecases;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.impl.OpenAndCloseRestaurant;
import com.br.rank.list.app.usecases.impl.OpenCloseRestaurant;
import com.br.rank.list.domains.Restaurant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OpenAndCloseRestaurantTest {

    @InjectMocks
    private OpenAndCloseRestaurant openAndCloseRestaurant;

    @Mock
    private IRestaurantRepository restaurantRepository;

    private final GetMockJson getMockJson = new GetMockJson();

    final String id = "123";

    private Restaurant restaurant;

    @Before
    public void init() {
        restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);

        when(restaurantRepository.findById(anyString()))
                .thenReturn(Optional.of(restaurant));
    }

    @Test
    public void testExecuteOpen() {

        when(restaurantRepository.save(any()))
                .thenReturn(restaurant.open());

        final var restaurantSave = restaurant.open();

        final var result = openAndCloseRestaurant.execute(id, OpenCloseRestaurant.OPEN);

        assertThat(result).isNotNull();
        verify(restaurantRepository, times(1)).save(restaurantSave);

    }


    @Test
    public void testExecuteClosed() {
        when(restaurantRepository.save(any()))
                .thenReturn(restaurant.close());

        final var restaurantSave = restaurant.close();

        final var result = openAndCloseRestaurant.execute(id, OpenCloseRestaurant.CLOSE);

        assertThat(result).isNotNull();
        verify(restaurantRepository, times(1)).save(restaurantSave);

    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testRestaurantNotFoundException() {
        when(restaurantRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        openAndCloseRestaurant.execute(id, OpenCloseRestaurant.CLOSE);
    }
}
