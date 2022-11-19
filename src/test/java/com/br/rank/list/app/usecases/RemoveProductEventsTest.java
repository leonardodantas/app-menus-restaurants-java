package com.br.rank.list.app.usecases;

import com.br.rank.list.app.messages.IRemoveProductMessage;
import com.br.rank.list.app.usecases.impl.RemoveProductEvents;
import com.br.rank.list.domains.RestaurantCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductEventsTest {

    @InjectMocks
    private RemoveProductEvents removeProductEvents;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private IRemoveProductMessage removeProductMessage;


    @Test
    public void testExecute() {
        final var id = "21321";
        final var code = "dasdad65464";

        removeProductEvents.execute(id, code);

        verify(applicationEventPublisher, times(1)).publishEvent(new RestaurantCode(code));
        verify(removeProductMessage, times(1)).execute(id);
    }
}
