
import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = Constants.REQRES_IN_API;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected RequestSpecification givenWithLogging() {
        return given()
                .filter(new AllureRestAssured())
                .header(Constants.X_API_KEY, Constants.VALID_API_KEY)
                .log().method()
                .log().uri()
                .log().body();
    }
}

