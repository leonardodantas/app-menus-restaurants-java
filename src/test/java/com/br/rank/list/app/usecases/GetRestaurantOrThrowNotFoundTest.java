package com.br.rank.list.app.usecases;

import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.impl.GetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetRestaurantOrThrowNotFoundTest {

    @InjectMocks
    private GetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    @Mock
    private IRestaurantRepository restaurantRepository;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var code = "123";

        final var restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);
        when(restaurantRepository.findByCode(anyString()))
                .thenReturn(Optional.of(restaurant));

        final var result = getRestaurantOrThrowNotFound.execute(code);

        assertThat(result).isNotNull();
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testRestaurantNotFoundException() {
        final var code = "123";
        when(restaurantRepository.findByCode(anyString()))
                .thenReturn(Optional.empty());

        getRestaurantOrThrowNotFound.execute(code);
    }
}
