package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.FindAllPromotionsRestaurant;
import com.br.rank.list.domains.Product;
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
public class FindAllPromotionsRestaurantTest {

    @InjectMocks
    private FindAllPromotionsRestaurant findAllPromotionsRestaurant;

    @Mock
    private IProductRepository productRepository;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var code = "123";
        final var type = new TypeReference<Collection<Product>>() {
        };

        final var products = getMockJson.execute("domains/products-valid", type);

        when(productRepository.findByCodeAndPromotionTrue(anyString()))
                .thenReturn(products);

        final var restaurantsWithPromotion = findAllPromotionsRestaurant.execute(code);

        assertThat(restaurantsWithPromotion).isNotEmpty();
    }
}
