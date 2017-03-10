package hu.roothema.homework.web;

import hu.roothema.homework.model.Data;
import hu.roothema.homework.repository.DataRepository;
import hu.roothema.homework.web.handler.Error;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataControllerTest {

    public static final String STORE_DATA_URL = "/v1/data";
    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        dataRepository.deleteAll();
    }

    @Test
    public void testStoreData() throws Exception {
        // given
        Data data = new Data("sample-data");

        // when
        ResponseEntity<Data> response = restTemplate.postForEntity(STORE_DATA_URL, data, Data.class);

        // then
        assertThat("Invalid status code", response.getStatusCodeValue(), equalTo(HttpStatus.CREATED.value()));
        assertThat("Empty payload", response.getBody(), notNullValue());
        assertThat("Absent ID", response.getBody().getId(), notNullValue());
        assertThat("Invalid data", response.getBody().getData(), equalTo(data.getData()));
    }

    @Test
    public void testStoreTwiceData() throws Exception {
        // given
        ResponseEntity<Data> firstResponse = restTemplate.postForEntity(STORE_DATA_URL, new Data("sample-data"), Data.class);

        // when
        ResponseEntity<Data> secondResponse = restTemplate.postForEntity(STORE_DATA_URL, new Data("sample-data-2"), Data.class);

        // then
        assertThat("Invalid status code", firstResponse.getStatusCodeValue(), equalTo(HttpStatus.CREATED.value()));
        assertThat("Invalid status code", secondResponse.getStatusCodeValue(), equalTo(HttpStatus.CREATED.value()));
        assertThat("First and Second response contain the same object", firstResponse.getBody(), not(equalTo(secondResponse.getBody())));
    }

    @Test
    public void testStoringEmptyData() throws Exception {
        // when
        ResponseEntity<Error> response = restTemplate.postForEntity(STORE_DATA_URL, new Data(), Error.class);

        // then
        assertThat("Invalid status code", response.getStatusCodeValue(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        assertThat("Invalid message", response.getBody().getMessage(), is("Empty data cannot be stored"));
    }

    @Test
    public void testStoringNullData() throws Exception {
        // when
        ResponseEntity<Error> response = restTemplate.postForEntity(STORE_DATA_URL, null, Error.class);

        // then
        assertThat("Invalid status code", response.getStatusCodeValue(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        assertThat("Invalid message", response.getBody().getMessage(), is("General error"));
    }
}