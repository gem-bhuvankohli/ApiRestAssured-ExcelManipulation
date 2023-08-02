package utils;

import io.restassured.response.Response;
import specbuilder.GoRestRequestSpecificationBuilder;
import static io.restassured.RestAssured.given;
public class GoRestUtils {
    public static Response getResponse(String endpoint) {
        return given(GoRestRequestSpecificationBuilder.getRequestSpecification())
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
    public static Response postResponse(String endpoint,Object payload) {
        return given(GoRestRequestSpecificationBuilder.getRequestSpecification())
                .body(payload)
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
}
