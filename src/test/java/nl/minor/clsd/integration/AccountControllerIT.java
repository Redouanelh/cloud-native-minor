package nl.minor.clsd.integration;

import nl.minor.clsd.application.error.NotFoundException;
import nl.minor.clsd.domain.entity.Account;
import nl.minor.clsd.presentation.AccountDto;
import nl.minor.clsd.presentation.requests.CreateAccountRequestDto;
import org.iban4j.CountryCode;
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
        var path = String.format("http://localhost:%s/api/account/NL39ABNA1234567890", this.port);
        var response = this.testRestTemplate.getForObject(path, AccountDto.class);

        assertThat(response.getIban()).isEqualTo("NL39ABNA1234567890");
    }

    @Test
    void get_all_accounts() {
        var path = String.format("http://localhost:%s/api/account", this.port);
        var response = this.testRestTemplate.getForObject(path, AccountDto[].class);

        assertThat(response).extracting("iban").contains("NL39ABNA1234567890");
        assertThat(response).extracting("iban").contains("NL39ABNA0987654321");
        assertThat(response).extracting("iban").contains("NL39INGB1234567890");
    }

    @Test
    void create_account() {
        var postPath = String.format("http://localhost:%s/api/account", this.port);
        CreateAccountRequestDto createAccountRequestDto = new CreateAccountRequestDto();
        createAccountRequestDto.setCountryCode(CountryCode.NL);
        createAccountRequestDto.setAccountNr(246L);
        createAccountRequestDto.setBankCode("ABNA");

        // save
        this.testRestTemplate.postForEntity(postPath, createAccountRequestDto, AccountDto.class);

        var getPath = String.format("http://localhost:%s/api/account/NL39ABNA0000000246", this.port);
        var response = this.testRestTemplate.getForObject(getPath, AccountDto.class);
        assertThat(response.getIban()).isEqualTo("NL39ABNA0000000246");
    }

    @Test
    void delete_account() {

    }

}
