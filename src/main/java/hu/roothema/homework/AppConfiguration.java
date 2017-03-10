package hu.roothema.homework;

import hu.roothema.homework.core.DatabaseEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

@Configuration
public class AppConfiguration {

    public static final String DATA_STORE_EVENT = "data-event";

    @Bean
    Environment environment() {
        return Environment.initializeIfEmpty().assignErrorJournal();
    }

    @Bean
    @Autowired
    public EventBus eventBus(Environment environment, DatabaseEventListener databaseEventListener) {
        EventBus eventBus = EventBus.create(environment, Environment.THREAD_POOL);

        eventBus.on($(DATA_STORE_EVENT), databaseEventListener);

        return eventBus;
    }
}
