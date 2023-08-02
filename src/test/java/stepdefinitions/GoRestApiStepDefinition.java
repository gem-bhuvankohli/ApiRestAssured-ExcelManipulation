package stepdefinitions;

import implementation.GoRestGetImplementation;
import implementation.GoRestPostImplementation;
import io.cucumber.java.en.Given;

public class GoRestApiStepDefinition {

    @Given("^Write data to xlsx file$")
    public static void getDataFromGoRestApi() {
        GoRestGetImplementation.getDataAndAddToXlsxFile();
    }

    @Given("^Post data on GoRest Api$")
    public static void postDataToGoRestApi() {
        GoRestPostImplementation.getDataFromXlsxFileAndPost();
    }
}
