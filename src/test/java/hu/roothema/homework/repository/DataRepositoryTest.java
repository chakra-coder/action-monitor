package hu.roothema.homework.repository;

import hu.roothema.homework.repository.entity.DataEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DataRepositoryTest {

    @Autowired
    private DataRepository dataRepository;

    @Before
    public void setUp() throws Exception {
        dataRepository.deleteAll();
    }

    @Test
    public void testStoreData() throws Exception {
        // given
        DataEntity dataEntity = new DataEntity("sample-data");

        // when
        DataEntity savedDataEntity = dataRepository.save(dataEntity);

        // then
        assertThat("Entity is null", savedDataEntity, notNullValue());
        assertThat("Id isn't generated", savedDataEntity.getId(), notNullValue());
        assertThat("Absent data value", savedDataEntity.getData(), is(dataEntity.getData()));
        assertThat("The stored and retrieved entity isn't the same", savedDataEntity, equalTo(dataRepository.findOne(savedDataEntity.getId())));
    }
}