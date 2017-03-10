package hu.roothema.homework.core;

import hu.roothema.homework.model.EventContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import reactor.bus.Event;
import reactor.fn.Consumer;

@Component
public class DatabaseEventListener implements Consumer<Event<EventContext>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseEventListener.class);

    private SimpMessagingTemplate template;

    @Autowired
    public DatabaseEventListener(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void accept(Event<EventContext> eventContextEvent) {
        EventContext eventContext = eventContextEvent.getData();

        LOGGER.info("Database event occurred. {}", eventContext);

        this.template.convertAndSend("/topic/action-message", eventContext);
    }
}
