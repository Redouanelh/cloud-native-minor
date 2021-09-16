package nl.minor.clsd.cucumber;

import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;

@Cucumber
@CucumberOptions(features = "src/test/resources/features")
public class RunCucumberTest {
}
