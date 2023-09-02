package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.ISearchRestaurantRepository;
import com.br.rank.list.app.usecases.impl.GetSuggestionsRestaurants;
import com.br.rank.list.domains.SearchRestaurant;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetSuggestionsRestaurantsTest {

    @InjectMocks
    private GetSuggestionsRestaurants getSuggestionsRestaurants;
    @Mock
    private ISearchRestaurantRepository searchRestaurantRepository;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {

        final var type = new TypeReference<Collection<SearchRestaurant>>() {};

        final var searchRestaurants = getMockJson.execute("domains/search-restaurant-valid", type);

        final var search = "132";

        when(searchRestaurantRepository.findByNameContaining(anyString()))
                .thenReturn(searchRestaurants);

        final var result = getSuggestionsRestaurants.execute(search);

        assertThat(result).isNotEmpty();
    }
}
