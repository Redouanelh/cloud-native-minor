package nl.minor.clsd.cucumber.steps;

import io.cucumber.java.en.When;

public class VersionControllerStep {

    @When("^the client calls /version$")
    void the_client_issues_GET_version() throws Throwable {
//        executeGet("http://localhost:8080/api/version");
    }
}
