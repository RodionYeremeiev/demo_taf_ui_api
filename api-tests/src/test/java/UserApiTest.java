import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import model.User;
import model.UserResponse;
import model.getsingleormultiuserresponse.GetMultiUserResponse;
import model.getsingleormultiuserresponse.GetSingleUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserApiTest extends BaseApiTest {

  User user = createUser();

  @Test
  @DisplayName("Verify that GET /users?page=2 returns a list of users")
  void getListOfUsersTest() {
    GetMultiUserResponse response =
        givenWithLogging()
            .when()
            .get("/users?page=2")
            .then()
            .log()
            .status()
            .log()
            .body()
            .statusCode(200)
            .extract()
            .as(GetMultiUserResponse.class);
    assertEquals(2, response.getPage(), ERROR_NOT_AS_EXPECTED.formatted("number"));
    assertFalse(response.getData().isEmpty(), ERROR_MUST_NOT_BE_EMPTY.formatted("data"));
  }

  @Test
  @DisplayName("Verify that GET /users/2 returns correct user details")
  void getSingleUserTest() {
    GetSingleUserResponse response =
        givenWithLogging()
            .when()
            .get("/users/2")
            .then()
            .log()
            .status()
            .log()
            .body()
            .statusCode(200)
                .extract()
            .as(GetSingleUserResponse.class);
    assertEquals(2, response.getData().getId(), ERROR_NOT_AS_EXPECTED.formatted("data.id"));
    assertTrue(response.getData().getEmail().contains("@reqres.in"), ERROR_NOT_AS_EXPECTED.formatted("email"));
  }

  @Test
  @DisplayName("Verify that POST /users creates a new user")
  void createUserWithValidTokenTest() {
    UserResponse userResponse =
        givenWithLogging()
            .contentType(ContentType.JSON)
            .body(user)
            .when()
            .post("/users")
            .then()
            .log()
            .status()
            .log()
            .body()
            .statusCode(201)
            .extract()
            .as(UserResponse.class);
    assertEquals(user.name, userResponse.getName(), ERROR_NOT_AS_EXPECTED.formatted("name"));
    assertEquals(user.job, userResponse.getJob(), ERROR_NOT_AS_EXPECTED.formatted("job"));
    assertFalse(userResponse.id.isEmpty(), ERROR_MUST_NOT_BE_EMPTY.formatted("id"));
    assertFalse(userResponse.createdAt.isEmpty(), ERROR_MUST_NOT_BE_EMPTY.formatted("createdAt"));
  }

  @Test
  @DisplayName("Verify that POST /users fail with 403 on a new user creation with invalid api-key")
  void createUserWithInvalidApiKeyTest() {
    givenWithLogging()
        .contentType(ContentType.JSON)
        .header("x-api-key", "invalid")
        .body(user)
        .when()
        .post("/users")
        .then()
        .log()
        .status()
        .log()
        .body()
        .statusCode(403)
        .body("error", equalTo(ERROR_INVALID_API_KEY));
  }

  private User createUser() {
    return User.builder().name("John").job("AQA Engineer").build();
  }
}
