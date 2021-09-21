package nl.minor.clsd.application;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DummyApiServiceIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startServer() {
        mockServer = startClientAndServer(9080);
    }

    @AfterAll
    public static void stopServer() {
        mockServer.stop();
    }

    private void createDummyApiMock() {
        new MockServerClient("127.0.0.1", 9080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/api/v1/employees"),
                        exactly(1) // verify the request is only done once
                ).respond(
                response()
                        .withStatusCode(200)
                        .withHeaders(
                                new Header("Content-Type", "application/json; charset=utf-8"),
                                new Header("Cache-Control", "public, max-age=86400"))
                .withBody(
                        "firstName"
                )
        );
    }

    @Test
    public void testGetEmployees() {

        //Given
        createDummyApiMock();

        //When
        String response = testRestTemplate.getForObject(("http://localhost:" + port + "/api/dummy"), String.class);

        //Test
        assertThat(response).contains("firstName");
    }
}
