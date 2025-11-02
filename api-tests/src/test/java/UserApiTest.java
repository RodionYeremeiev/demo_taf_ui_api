import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest {
  @Test
  void getUserShouldReturn200() {
    RestAssured.baseURI = "https://api.example.com";
    given()
            .when()
            .get("/users/1")
            .then()
            .statusCode(200)
            .body("name", equalTo("John Doe"));
  }
}
