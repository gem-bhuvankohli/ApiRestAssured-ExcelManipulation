package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/BookerApiTest.feature",
                "src/test/resources/GetUserGoRestApi.feature",
                "src/test/resources/PostUserGoRestApi.feature"
        },
        glue = "stepdefinitions",
        tags = "@testApi",
        publish = true
)

public class TestRunner {
}

