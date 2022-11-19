package com.br.rank.list.app.usecases;


import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.impl.FindPageRestaurants;
import com.br.rank.list.domains.Restaurant;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import utils.GetMockJson;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindPageRestaurantsTest {

    @InjectMocks
    private FindPageRestaurants findPageRestaurants;
    @Mock
    private IRestaurantRepository restaurantRepository;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {

        final var type = new TypeReference<Collection<Restaurant>>() {
        };

        final var restaurants = getMockJson.execute("domains/restaurants-valid", type);

        when(restaurantRepository.findActiveRestaurants(anyInt(), anyInt()))
                .thenReturn(new PageImpl<>(restaurants.stream().toList()));

        final var page = 0;
        final var size = 20;

        final var result = findPageRestaurants.execute(page, size);

        assertThat(result).isNotNull();
        assertThat(result.getContent().get(0).isActive()).isTrue();
        assertThat(result.getContent().get(3).isActive()).isFalse();
    }

}
