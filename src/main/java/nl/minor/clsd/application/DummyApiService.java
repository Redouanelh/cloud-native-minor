package nl.minor.clsd.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DummyApiService {

    private final RestTemplate restTemplate;

    @Value("${dummy.service}")
    private String host;

    public DummyApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getEmployees() {
        return restTemplate.getForObject("http://" + host + "/api/v1/employees", String.class);
    }
}
