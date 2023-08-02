package stepdefinitions;

import implementation.BookerApiImplementation;

import implementation.GoRestGetImplementation;
import implementation.GoRestPostImplementation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookerApiStepDefinition {

    @Given("^Run ping health check after each request$")
    public static void pingHealthCheck() {
        BookerApiImplementation.apiHealthCheck();
    }

    @Then("^Create new auth token$")
    public static void createNewAuthToken() {
        BookerApiImplementation.createToken();
    }

    @When("^Get all booking ids$")
    public static void getAllBookingIds() {
        BookerApiImplementation.getBookingIds();
    }

    @Then("^Get details of any booking id$")
    public static void getAnyBooking() {
        BookerApiImplementation.getAnyBooking();
    }

    @Then("^Create a new booking$")
    public static void createNewBooking() {
        BookerApiImplementation.createBooking();
    }

    @And("^Get details of the booking id$")
    public static void getBookingIdDetail() {
        BookerApiImplementation.getBooking();
    }

    @Then("^Update booking id$")
    public static void updateBooking() {
        BookerApiImplementation.updateBooking();
    }

    @Then("^Partially update booking id$")
    public static void partialUpdateBooking() {
        BookerApiImplementation.partialUpdateBooking();
    }

    @When("^Delete the booking id$")
    public static void deleteBooking() {
        BookerApiImplementation.deleteBooking();
    }

    @Then("^Check if id deleted$")
    public static void validateDelete() {
        BookerApiImplementation.getBooking();
    }
}



