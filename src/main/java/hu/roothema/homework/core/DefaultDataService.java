package hu.roothema.homework.core;

import hu.roothema.homework.model.Data;
import hu.roothema.homework.repository.DataRepository;
import hu.roothema.homework.repository.entity.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultDataService implements DataService {

    private final DataRepository dataRepository;

    @Autowired
    public DefaultDataService(final DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public Data store(Data data) {
        DataEntity dataEntity = dataRepository.save(new DataEntity(data.getData()));
        return new Data(dataEntity.getId(), dataEntity.getData());
    }

}
