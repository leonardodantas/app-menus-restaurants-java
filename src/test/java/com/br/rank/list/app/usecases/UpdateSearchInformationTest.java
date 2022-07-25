package com.br.rank.list.app.usecases;

import com.br.rank.list.app.repositories.ISearchInformationRepository;
import com.br.rank.list.app.usecases.impl.UpdateSearchInformation;
import com.br.rank.list.domains.SearchInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utils.GetMockJson;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateSearchInformationTest {

    @InjectMocks
    private UpdateSearchInformation updateSearchInformation;

    @Mock
    private ISearchInformationRepository searchInformationRepository;

    @Captor
    private ArgumentCaptor<SearchInformation> argumentCaptor;

    private final GetMockJson getMockJson = new GetMockJson();

    @Test
    public void testExecute() {
        final var searchInformation = getMockJson.execute("domains/search-information", SearchInformation.class);

        when(searchInformationRepository.findByProductId(anyString()))
                .thenReturn(Optional.of(searchInformation));

        updateSearchInformation.execute(searchInformation);

        verify(searchInformationRepository, times(1)).save(argumentCaptor.capture());


        final var searchInformationUpdate = argumentCaptor.getValue();

        assertNotNull(searchInformationUpdate);
        assertEquals(searchInformation.getProductId(), searchInformationUpdate.getProductId());
    }

    @Test
    public void testExecuteNew() {
        final var searchInformation = getMockJson.execute("domains/search-information", SearchInformation.class);

        when(searchInformationRepository.findByProductId(anyString()))
                .thenReturn(Optional.empty());

        updateSearchInformation.execute(searchInformation);

        verify(searchInformationRepository, times(1)).save(argumentCaptor.capture());


        final var searchInformationUpdate = argumentCaptor.getValue();
        assertNotNull(searchInformationUpdate);
    }
}
