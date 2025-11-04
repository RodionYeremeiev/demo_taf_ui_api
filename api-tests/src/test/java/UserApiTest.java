import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest {

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = "https://reqres.in/api";
  }

  @Test
  void getListOfUsers() {
    given()
            .when()
            .get("/users?page=2")
            .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("data", not(empty()));
  }

  @Test
  void getSingleUser() {
    given()
            .when()
            .get("/users/2")
            .then()
            .statusCode(200)
            .body("data.id", equalTo(2));
  }

  @Test
  void createUser() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"name\": \"Rod\", \"job\": \"QA\"}")
            .when()
            .post("/users")
            .then()
            .statusCode(201)
            .body("name", equalTo("Rod"))
            .body("job", equalTo("QA"))
            .body("id", notNullValue());
  }

}
