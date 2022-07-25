package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.ISearchInformationRepository;
import com.br.rank.list.app.usecases.impl.RemoveSearchInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RemoveSearchInformationTest {

    @InjectMocks
    private RemoveSearchInformation removeSearchInformation;

    @Mock
    private ISearchInformationRepository searchInformationRepository;

    @Test
    public void testExecute() {
        final var productId = "132";

        removeSearchInformation.execute(productId);

        verify(searchInformationRepository, times(1)).removeByProductId(productId);
    }
}
