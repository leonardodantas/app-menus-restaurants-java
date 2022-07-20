package com.br.rank.list.app.usecases;

import com.br.rank.list.app.rest.ISearchProductsAllRestaurantsRest;
import com.br.rank.list.app.usecases.impl.GetSuggestionsProductsAllRestaurants;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.SearchProduct;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetSuggestionsProductsAllRestaurantsTest {

    @InjectMocks
    private GetSuggestionsProductsAllRestaurants getSuggestionsProductsAllRestaurants;

    @Mock
    private ISearchProductsAllRestaurantsRest searchProductsAllRestaurantsRest;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute(){
        final var search = "Cachorro quente";

        final var type = new TypeReference<Collection<Product>>() {
        };

        final var searchProducts = getMockJson.execute("domains/products-valid", type);

        when(searchProductsAllRestaurantsRest.execute(anyString()))
                .thenReturn(searchProducts);

        final var result = getSuggestionsProductsAllRestaurants.execute(search);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
