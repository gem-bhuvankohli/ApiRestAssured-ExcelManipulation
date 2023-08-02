package implementation;

import logger.Log;
import pojo.BookingDetails;
import pojo.InOutTime;
import pojo.UserDetails;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.BookerRestUtils;

public class BookerApiImplementation {
    static String bookingID;

    /*
     * Health check by hitting ping Get request on GoRestApi.
     * @return Api health check.
     */
    public static void apiHealthCheck() {
        Response res = BookerRestUtils.getResponse("/ping", "");
        Assert.assertEquals(res.getStatusCode(), 201);
        Log.info("Api is up and running!");
    }

    /*
     * Creating authentication token by hitting auth Post request on GoRest Api.
     * @return Auth Token.
     */
    public static void createToken() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername("admin");
        userDetails.setPassword("password123");
        Response res = BookerRestUtils.postResponse("/auth", userDetails);
        if (res.getStatusCode() == 200) {
            Log.info("Token created successfully!");
            Log.info("Token : " + res.path("token"));
        } else {
            Log.error("Failed to generate token");
            Log.info("Response Code " + res.getStatusCode());
        }
    }

    /*
     * Get all bookings on GoRestApi using Get request of GoRestApi.
     * @return All bookings on GoRestApi.
     */
    public static void getBookingIds() {
        Response res = BookerRestUtils.getResponse("booking", "");
        Assert.assertEquals(res.getStatusCode(), 200);
        Log.info("Booking IDs fetched successfully!");
    }

    /*
     * Get any booking of all the bookings on GoRestApi.
     * @return booking details of id 1654.
     */
    public static void getAnyBooking() {
        Response res = BookerRestUtils.getResponse("booking", "1654");
        Assert.assertEquals(res.getStatusCode(), 200);
        Log.info("Booking IDs fetched successfully!");
    }

    /*
     * Create a booking on GoRestApi using Post request.
     * @return 201 Created Response.
     */
    public static void createBooking() {
        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setFirstname("bhuvan");
        bookingDetails.setLastname("kohli");
        bookingDetails.setTotalprice(222);
        bookingDetails.setDepositpaid(true);
        InOutTime inOutTime = new InOutTime();
        inOutTime.setCheckin("2020-10-10");
        inOutTime.setCheckout("2020-11-10");
        bookingDetails.setBookingdates(inOutTime);
        bookingDetails.setAdditionalneeds("Lunch");

        Response res = BookerRestUtils.postResponse("/booking", bookingDetails);
        bookingID = res.path("bookingid").toString();
        Assert.assertEquals(res.getStatusCode(), 200);
        Log.info("Booking created successfully!\nId: " + bookingID);
    }

    /*
     * Updates booking details of the created booking using Put request.
     * @return updated details.
     */
    public static void updateBooking() {
        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setFirstname("changedName");
        bookingDetails.setLastname("changedLastName");
        bookingDetails.setTotalprice(222);
        bookingDetails.setDepositpaid(true);
        InOutTime inOutTime = new InOutTime();
        inOutTime.setCheckin("2020-10-10");
        inOutTime.setCheckout("2020-11-10");
        bookingDetails.setBookingdates(inOutTime);
        bookingDetails.setAdditionalneeds("Lunch");

        Response res = BookerRestUtils.putResponse("booking/", bookingDetails, bookingID);
        Assert.assertEquals(res.getStatusCode(), 200);
        Log.info("Booking details updated successfully!");
    }

    /*
     * Partially Updates booking details of the created booking using Patch request.
     * @return updated details.
     */
    public static void partialUpdateBooking() {
        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setFirstname("changedName123");
        bookingDetails.setLastname("changedLastName123");

        Response res = BookerRestUtils.patchResponse("booking/", bookingDetails, bookingID);
        Assert.assertEquals(res.getStatusCode(), 200);
        Log.info("Booking details partially updated!");
    }

    /*
     * Gets booking details of the created,updated or partially updated booking using Get request.
     * @return booking details.
     */

    public static void getBooking() {
        Response res = BookerRestUtils.getResponse("booking/", bookingID);
        Assert.assertEquals(res.getStatusCode(), 200);
        Log.info("Booking details fetched for Id: " + bookingID);
    }


    /*
     * deletes the created booking using Delete request of GoRestApi.
     * @return successfully deletes the booking.
     */
    public static void deleteBooking() {
        Response res = BookerRestUtils.deleteResponse("booking/", bookingID);
        Assert.assertEquals(res.getStatusCode(), 201);
        Log.info("Booking deleted successfully!");
    }
}
