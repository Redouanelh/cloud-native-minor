package nl.minor.clsd.functional.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.minor.clsd.domain.entity.Account;
import nl.minor.clsd.domain.entity.AccountHolder;
import nl.minor.clsd.presentation.AccountDto;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountDefStep extends AbstractStepDef {

    @Given("I have an Iban code {string}")
    public void iHaveAnIbanCode(String ibanCode) {
        testContext().reset();
        testContext().setPayload(ibanCode);
    }

    @When("I try and retrieve the holders of the account")
    public void iTryAndRetrieveTheHoldersOfTheAccountWith() {
        final var iban = testContext().getPayload(String.class);
        String url = "/account/" + iban;
        executeGet(url);
    }

    @Then("I get the expected holder names")
    public void iGetTheTheHoldersOfTheAccount(List<String> holders) {
        final var response = testContext().getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getObject(".", AccountDto.class);

        final var responseHolders = response.getAccountHolders();

        assertThat(responseHolders)
                .extracting(AccountHolder::getFirstName)
                .containsAnyElementsOf(holders);
    }

}
