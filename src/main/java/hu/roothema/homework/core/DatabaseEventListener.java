package hu.roothema.homework.core;

import hu.roothema.homework.model.EventContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.bus.Event;
import reactor.fn.Consumer;

@Component
public class DatabaseEventListener implements Consumer<Event<EventContext>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseEventListener.class);

    @Override
    public void accept(Event<EventContext> eventContextEvent) {
        LOGGER.info("Database event occurred. {}", eventContextEvent.getData());
    }
}
