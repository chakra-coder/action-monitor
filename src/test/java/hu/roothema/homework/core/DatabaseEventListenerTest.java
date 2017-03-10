package hu.roothema.homework.core;

import hu.roothema.homework.model.EventContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import reactor.bus.Event;

import static org.mockito.Matchers.any;

public class DatabaseEventListenerTest {

    private SimpMessagingTemplate mockSimpMessagingTemplate = Mockito.mock(SimpMessagingTemplate.class);
    private DatabaseEventListener databaseEventListener;

    @Before
    public void setUp() throws Exception {
        databaseEventListener = new DatabaseEventListener(mockSimpMessagingTemplate);
    }

    @Test
    public void testEventDispatchingViaWebsocket() throws Exception {
        // given
        Event<EventContext> event = Event.wrap(new EventContext(1L, "data"));

        // when
        databaseEventListener.accept(event);

        // then
        Mockito.verify(mockSimpMessagingTemplate, Mockito.times(1)).convertAndSend(any(String.class), any(EventContext.class));
    }
}