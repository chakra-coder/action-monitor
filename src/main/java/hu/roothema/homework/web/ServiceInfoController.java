package hu.roothema.homework.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class ServiceInfoController {

    @RequestMapping(value = "/v1/state", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getServiceState() {
        Set<String> dummyResponse = new HashSet<>();
        dummyResponse.add("OK");

        return ResponseEntity.ok(dummyResponse);
    }

}
