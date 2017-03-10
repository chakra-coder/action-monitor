package hu.roothema.homework.core;

import hu.roothema.homework.model.Data;
import hu.roothema.homework.model.EventContext;
import hu.roothema.homework.repository.DataRepository;
import hu.roothema.homework.repository.entity.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

import static hu.roothema.homework.AppConfiguration.DATA_STORE_EVENT;

@Service
public class DefaultDataService implements DataService {

    private final DataRepository dataRepository;
    private final EventBus eventBus;

    @Autowired
    public DefaultDataService(DataRepository dataRepository, EventBus eventBus) {
        this.dataRepository = dataRepository;
        this.eventBus = eventBus;
    }

    @Override
    public Data store(Data data) {
        DataEntity dataEntity = dataRepository.save(new DataEntity(data.getData()));
        Data storedData = new Data(dataEntity.getId(), dataEntity.getData());

        eventBus.notify(DATA_STORE_EVENT, Event.wrap(new EventContext(storedData.getId(), storedData.getData())));

        return storedData;
    }

}
