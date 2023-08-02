package specbuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GoRestRequestSpecificationBuilder {
    public static Properties properties = new Properties();
    public static String baseUri;

    public static void properties() {
        try (InputStream inputStream = new FileInputStream("src/config.properties")) {
            properties.load(inputStream);

            baseUri = properties.getProperty("baseUriGoRest");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RequestSpecification getRequestSpecification() {
        properties();
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer 50ac4f4709843853393a0c78f00be1659cad8f207f4df0fdbdfe2e4faeb84e63")
                .log(LogDetail.ALL)
                .build();
    }

}
