package hu.roothema.homework.web;

import hu.roothema.homework.core.DataService;
import hu.roothema.homework.model.Data;
import hu.roothema.homework.web.validator.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @Autowired
    private DataValidator dataValidator;

    @RequestMapping(value = "/v1/data", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity storeData(@RequestBody Data data) {

        dataValidator.validate(data);

        Data storeData = dataService.store(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeData);
    }
}
