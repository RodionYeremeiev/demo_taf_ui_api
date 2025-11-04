
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class UserApiTest extends BaseApiTest {

  @Test
  @Description("Verify that GET /users?page=2 returns a list of users")
  void getListOfUsers() {
    givenWithLogging()
            .when()
            .get("/users?page=2")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("data", not(empty()));
  }

  @Test
  @Description("Verify that GET /users/2 returns correct user details")
  void getSingleUser() {
    givenWithLogging()
            .when()
            .get("/users/2")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", containsString("@reqres.in"));
  }

  @Test
  @Description("Verify that POST /users creates a new user")
  void createUser() {
    givenWithLogging()
            .contentType(ContentType.JSON)
            .body("{\"name\": \"Rodion\", \"job\": \"QA Engineer\"}")
            .when()
            .post("/users")
            .then()
            .log().status()
            .log().body()
            .statusCode(201)
            .body("name", equalTo("Rodion"))
            .body("job", equalTo("QA Engineer"))
            .body("id", notNullValue())
            .body("createdAt", notNullValue());
  }

  @Test
  @Description("Verify that POST /users fail with 403 on a new user creates with invalid api-key")
  void createUserWithInvalidApiKey() {
    givenWithLogging()
            .contentType(ContentType.JSON)
            .body("{\"name\": \"Rodion\", \"job\": \"QA Engineer\"}")
            .header("x-api-key","invalid")
            .when()
            .post("/users")
            .then()
            .log().status()
            .log().body()
            .statusCode(403)
            .body("error", equalTo("Invalid or inactive API key"));
  }
}
