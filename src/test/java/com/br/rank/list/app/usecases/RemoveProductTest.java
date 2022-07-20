package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.impl.RemoveProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductTest {

    @InjectMocks
    private RemoveProduct removeProduct;

    @Mock
    private IProductRepository productRepository;
    @Mock
    private IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;
    @Mock
    private IRemoveProductEvents removeProductEvents;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var id = "123";
        final var code = "REST-465";

        removeProduct.execute(id, code);

        verify(getRestaurantOrThrowNotFound, times(1)).execute(code);
        verify(productRepository, times(1)).removeById(id);
        verify(removeProductEvents, times(1)).execute(id, code);
    }
}
