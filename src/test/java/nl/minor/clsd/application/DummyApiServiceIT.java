package nl.minor.clsd.application;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.model.Header;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class DummyApiServiceIT {

    private int port = 9080;

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
        new MockServerClient("127.0.0.1", this.port)
                .when(
                        request()
                        .withMethod("GET")
                        .withPath("/api/v1/employees"),
                    Times.exactly(1)
                ).respond(
                        HttpResponse.response()
                .withStatusCode(200)
                .withHeaders(
                        new Header("Content-Type", "application/json; charset=utf-8"),
                        new Header("Cache-Control", "public, max-age=86400"))
                .withBody("[\n" +
                        "  {\n" +
                        "    \"firstName\": \"Redouan\",\n" +
                        "    \"lastName\": \"Kaasje\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"firstName\": \"Hans\",\n" +
                        "    \"lastName\": \"Anders\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"firstName\": \"Pim\",\n" +
                        "    \"lastName\": \"Paas\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"firstName\": \"Albert\",\n" +
                        "    \"lastName\": \"Heijn\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"firstName\": \"Aldi\",\n" +
                        "    \"lastName\": \"Lidl\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"firstName\": \"Boeren\",\n" +
                        "    \"lastName\": \"Baard\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"firstName\": \"Waila\",\n" +
                        "    \"lastName\": \"Baila\"\n" +
                        "  }\n" +
                        "]")
        );
    }

    @Test
    public void mock_get_accountholders() {
        this.createDummyApiMock();

        String response = this.testRestTemplate.getForObject(("http://localhost:" + this.port + "/dummy"), String.class);

        assertThat(response).contains("firstName");
    }
}
