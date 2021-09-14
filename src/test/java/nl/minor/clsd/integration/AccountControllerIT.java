package nl.minor.clsd.integration;

import nl.minor.clsd.application.error.NotFoundException;
import nl.minor.clsd.presentation.AccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void get_account() {
        var path = String.format("http://localhost:%s/api/account/NLABNA1234567890", this.port);
        var response = this.testRestTemplate.getForObject(path, AccountDto.class);
        assertThat(response.getIban()).isEqualTo("NLABNA1234567890");
    }

//    @Test
//    void get_all_accounts() {
//        var path = String.format("http://localhost:%s/api/account", this.port);
//        var response = this.testRestTemplate.getForObject(path, AccountDto.class);
//        assertThat(response.getAccountHolders().size()).isEqualTo(6);
//    }


}
