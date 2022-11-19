package com.br.rank.list.app.usecases;


import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.FindAllPromotionsRestaurantNow;
import com.br.rank.list.domains.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindAllPromotionsRestaurantNowTest {

    @InjectMocks
    private FindAllPromotionsRestaurantNow findAllPromotionsRestaurantNow;

    @Mock
    private IProductRepository productRepository;
    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var code = "123";
        final var day = DayOfWeek.from(LocalDate.now()).name();
        final var type = new TypeReference<Collection<Product>>() {
        };

        final var products = getMockJson.execute("domains/products-with-promotion-valid", type);

        when(productRepository.findAllByCodeAndPromotionTrueCacheable(anyString()))
                .thenReturn(products);

        final var productsInPromotion = findAllPromotionsRestaurantNow.execute(code);

        assertNotNull(productsInPromotion);
        assertEquals(7, productsInPromotion.size());

        final var promotions = productsInPromotion.stream().map(Product::getPromotion).toList();
        final var dayAndHours = promotions.get(0).getDayAndHours().stream().filter(dayAndHour -> dayAndHour.getDay().equalsIgnoreCase(day)).findFirst();

        assertThat(dayAndHours.isPresent()).isTrue();
        assertThat(promotions.size()).isEqualTo(7);
        assertThat(promotions.get(0).getDayAndHours().size()).isEqualTo(1);
    }

    @Test
    public void testExecuteProductsWithPromotionsInvalid() {
        final var code = "123";
        final var type = new TypeReference<Collection<Product>>() {
        };

        final var products = getMockJson.execute("domains/products-without-promotion-valid", type);

        when(productRepository.findAllByCodeAndPromotionTrueCacheable(anyString()))
                .thenReturn(products);

        final var productsInPromotion = findAllPromotionsRestaurantNow.execute(code);

        assertThat(productsInPromotion).isNotNull();
        assertThat(productsInPromotion).isEmpty();
    }

}
