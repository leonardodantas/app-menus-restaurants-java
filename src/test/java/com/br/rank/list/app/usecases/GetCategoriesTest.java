package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository;
import com.br.rank.list.app.usecases.impl.GetCategories;
import com.br.rank.list.domains.RestaurantCategories;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetCategoriesTest {

    @InjectMocks
    private GetCategories getCategories;
    @Mock
    private IRestaurantCategoriesRepository restaurantCategoriesRepository;
    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var restaurantCategories = getMockJson.execute("domains/restaurant-categories", RestaurantCategories.class);

        when(restaurantCategoriesRepository.findByCode(anyString()))
                .thenReturn(Optional.of(restaurantCategories));

        final var code = "123";
        final var categories = getCategories.execute(code);

        assertNotNull(categories);
        assertFalse(categories.getValues().isEmpty());
    }
}
