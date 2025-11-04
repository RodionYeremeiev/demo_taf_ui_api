
import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseApiTest {

    public static final String ERROR_NOT_AS_EXPECTED = "Error: %s is not as expected";
    public static final String ERROR_MUST_NOT_BE_EMPTY = "Error: %s must no be empty";
    public static final String ERROR_INVALID_API_KEY = "Invalid or inactive API key";

    public static final String VALID_API_KEY = System.getProperty("api.key");

    public static final String REQRES_IN_API = "https://reqres.in/api";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = REQRES_IN_API;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected RequestSpecification givenWithLogging() {
        return given()
                .filter(new AllureRestAssured())
                .header("x-api-key", VALID_API_KEY)
                .log().method()
                .log().uri()
                .log().body();
    }
}

