package nl.minor.clsd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DummyApiService {

    private final RestTemplate restTemplate;

    public DummyApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getEmployees() {
        return this.restTemplate.getForObject("http://dummy.restapiexample.com/api/v1/employees", String.class);
    }

}
