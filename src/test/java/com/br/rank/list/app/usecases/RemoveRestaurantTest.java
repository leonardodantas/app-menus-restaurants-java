package com.br.rank.list.app.usecases;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.impl.RemoveRestaurant;
import com.br.rank.list.domains.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveRestaurantTest {

    @InjectMocks
    private RemoveRestaurant removeRestaurant;

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Captor
    private ArgumentCaptor<Restaurant> argumentCaptor;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute(){
        final var restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);

        when(restaurantRepository.findById(anyString()))
                .thenReturn(Optional.of(restaurant));

        final var id = "132";

        removeRestaurant.execute(id);

        verify(restaurantRepository, times(1)).save(argumentCaptor.capture());

        final var restaurantRemoved = argumentCaptor.getValue();

        assertFalse(restaurantRemoved.isActive());
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testRestaurantNotFoundException(){

        when(restaurantRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        final var id = "132";

        removeRestaurant.execute(id);

    }
}
