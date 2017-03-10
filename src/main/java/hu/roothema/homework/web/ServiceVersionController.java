package hu.roothema.homework.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ServiceVersionController {

    @Value("${application.version}")
    private String applicationVersion;

    @RequestMapping(value = "/v1/version", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getServiceVersion() {
        HashMap<String, String> serviceInformation = new HashMap<>();
        serviceInformation.put("application.version", applicationVersion);

        return ResponseEntity.ok(serviceInformation);
    }
}
