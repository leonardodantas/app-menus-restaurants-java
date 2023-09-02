package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.ISearchProductRepository;
import com.br.rank.list.app.usecases.impl.GetSuggestionsProducts;
import com.br.rank.list.domains.SearchProduct;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetSuggestionsProductsTest {

    @InjectMocks
    private GetSuggestionsProducts getSuggestionsProducts;
    @Mock
    private ISearchProductRepository searchProductRepository;
    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var type = new TypeReference<Collection<SearchProduct>>() {
        };

        final var searchProducts = getMockJson.execute("domains/search-product-valid", type);

        final var code = "123";
        final var search = "Cachorro quente";

        when(searchProductRepository.findByCodeAndNameContaining(anyString(), anyString()))
                .thenReturn(searchProducts);

        final var result = getSuggestionsProducts.execute(code, search);

        assertThat(result).isNotEmpty();
    }
}
