package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.impl.FindRestaurant;
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
public class FindRestaurantTest {

    @InjectMocks
    private FindRestaurant findRestaurant;
    @Mock
    private IRestaurantRepository restaurantRepository;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testFindById() {

        final var restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);

        when(restaurantRepository.findById(anyString()))
                .thenReturn(Optional.of(restaurant));

        final var id = "1";

        final var result = findRestaurant.findById(id);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void testFindByCNPJ() {

        final var restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);

        when(restaurantRepository.findByCnpj(anyString()))
                .thenReturn(Optional.of(restaurant));

        final var id = "1";

        final var result = findRestaurant.findByCnpj(id);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void testFindByCode() {

        final var restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);

        when(restaurantRepository.findByCode(anyString()))
                .thenReturn(Optional.of(restaurant));

        final var id = "1";

        final var result = findRestaurant.findByCode(id);

        assertThat(result.isPresent()).isTrue();
    }

}
