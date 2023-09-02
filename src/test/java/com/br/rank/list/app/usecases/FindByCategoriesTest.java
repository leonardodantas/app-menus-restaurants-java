package com.br.rank.list.app.usecases;


import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository;
import com.br.rank.list.app.usecases.impl.FindByCategories;
import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.RestaurantCategories;
import com.br.rank.list.infra.http.jsons.requests.CategoriesRequestJson;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindByCategoriesTest {

    @InjectMocks
    private FindByCategories findByCategories;
    @Mock
    private IRestaurantCategoriesRepository restaurantCategoriesRepository;
    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;
    @Mock
    private IProductRepository productRepository;

    private final GetMockJson getMockJson = new GetMockJson();

    private final String code = "132";
    private CategoriesRequestJson categoriesRequest;
    private RestaurantCategories restaurantCategories;
    private Collection<Product> products;

    @Before
    public void init() {
        this.categoriesRequest = getMockJson.execute("requests/categories-request", CategoriesRequestJson.class);
        this.restaurantCategories = getMockJson.execute("domains/restaurant-categories", RestaurantCategories.class);

        final var type = new TypeReference<Collection<Product>>() {
        };

        this.products = getMockJson.execute("domains/products-valid", type);

        when(restaurantCategoriesRepository.findByCode(anyString()))
                .thenReturn(Optional.of(restaurantCategories));
    }

    @Test
    public void testExecute() {
        when(productRepository.findAllByCodeAndCategories(anyString(), any()))
                .thenReturn(products);

        final var result = findByCategories.execute(code, Categories.from(categoriesRequest.values()));

        assertThat(result.size()).isZero();
        assertThat(result).isNotNull();
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void testRestaurantNotFoundException() {
        when(restaurantCategoriesRepository.findByCode(anyString()))
                .thenReturn(Optional.empty());

        findByCategories.execute(code, Categories.from(categoriesRequest.values()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        final var categoriesInvalidRequest = getMockJson.execute("requests/categories-invalid-request", CategoriesRequestJson.class);
        findByCategories.execute(code, Categories.from(categoriesInvalidRequest.values()));
    }
}
