
import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseApiTest {

    public static final String ERROR_NOT_AS_EXPECTED = "Error: %s is not as expected";
    public static final String ERROR_MUST_NOT_BE_EMPTY = "Error: %s must no be empty";
    public static final String ERROR_INVALID_API_KEY = "Invalid or inactive API key";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected RequestSpecification givenWithLogging() {
        return given()
                .filter(new AllureRestAssured())
                .header("x-api-key","reqres-free-v1")
                .log().method()
                .log().uri()
                .log().body();
    }
}

