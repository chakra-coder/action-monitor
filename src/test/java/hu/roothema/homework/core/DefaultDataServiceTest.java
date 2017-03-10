package hu.roothema.homework.core;

import hu.roothema.homework.model.Data;
import hu.roothema.homework.repository.DataRepository;
import hu.roothema.homework.repository.entity.DataEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.bus.Event;
import reactor.bus.EventBus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.any;

public class DefaultDataServiceTest {

    private DataService dataService;
    private DataRepository mockDataRepository = Mockito.mock(DataRepository.class);
    private EventBus mockEventBus = Mockito.mock(EventBus.class);

    @Before
    public void setUp() throws Exception {
        dataService = new DefaultDataService(mockDataRepository, mockEventBus);
    }

    @Test
    public void testDataStoringFlow() throws Exception {
        // given
        Data data = new Data("data");
        Mockito.when(mockDataRepository.save(any(DataEntity.class))).thenReturn(new DataEntity(1L, data.getData()));

        // when
        Data storedData = dataService.store(data);

        // then
        assertThat("Stored data is null", storedData, notNullValue());

        Mockito.verify(mockDataRepository, Mockito.times(1)).save(any(DataEntity.class));
        Mockito.verify(mockEventBus, Mockito.times(1)).notify(Mockito.anyString(), any(Event.class));
    }
}