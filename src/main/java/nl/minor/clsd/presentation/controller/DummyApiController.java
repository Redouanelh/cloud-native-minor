package nl.minor.clsd.presentation.controller;

import nl.minor.clsd.application.DummyApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dummy")
public class DummyApiController {

    private final DummyApiService dummyApiService;

    public DummyApiController(DummyApiService dummyApiService) {
        this.dummyApiService = dummyApiService;
    }

    @GetMapping
    public ResponseEntity<String> getEmployees() {
        return ResponseEntity.ok(this.dummyApiService.getEmployees());
    }
}
