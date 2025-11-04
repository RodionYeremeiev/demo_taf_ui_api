import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import model.User;
import model.UserResponse;
import model.getuser.GetMultiUserResponse;
import model.getuser.GetSingleUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserApiTest extends BaseApiTest {

  @Test
  @DisplayName("Verify that GET /users?page=2 returns a list of users")
  void getListOfUsersTest() {
    GetMultiUserResponse response =
            givenWithLogging()
                    .when()
                    .get(Constants.GET_MULTI_USER_ENDPOINT)
                    .then()
                    .log()
                    .status()
                    .log()
                    .body()
                    .statusCode(200)
                    .extract()
                    .as(GetMultiUserResponse.class);
    assertEquals(2, response.getPage(), Constants.ERROR_NOT_AS_EXPECTED.formatted("number"));
    assertFalse(response.getData().isEmpty(), Constants.ERROR_MUST_NOT_BE_EMPTY.formatted("data"));
  }

  @Test
  @DisplayName("Verify that GET /users/2 returns correct single user details")
  void getSingleUserTest() {
    GetSingleUserResponse response =
            givenWithLogging()
                    .when()
                    .get(Constants.GET_SINGLE_USER_ENDPOINT)
                    .then()
                    .log()
                    .status()
                    .log()
                    .body()
                    .statusCode(200)
                    .extract()
                    .as(GetSingleUserResponse.class);
    assertEquals(2, response.getData().getId(), Constants.ERROR_NOT_AS_EXPECTED.formatted("data.id"));
    assertTrue(
            response.getData().getEmail().contains("@reqres.in"),
            Constants.ERROR_NOT_AS_EXPECTED.formatted("email"));
  }

  @Test
  @DisplayName("Verify that POST /users creates a new user")
  void createUserWithValidTokenTest() {
    User user = createUser("John");
    UserResponse userResponse =
            givenWithLogging()
                    .contentType(ContentType.JSON)
                    .body(user)
                    .when()
                    .post(Constants.POST_USER_ENDPOINT)
                    .then()
                    .log()
                    .status()
                    .log()
                    .body()
                    .statusCode(201)
                    .extract()
                    .as(UserResponse.class);
    assertEquals(user.name, userResponse.getName(), Constants.ERROR_NOT_AS_EXPECTED.formatted("name"));
    assertEquals(user.job, userResponse.getJob(), Constants.ERROR_NOT_AS_EXPECTED.formatted("job"));
    assertFalse(userResponse.id.isEmpty(), Constants.ERROR_MUST_NOT_BE_EMPTY.formatted("id"));
    assertFalse(userResponse.createdAt.isEmpty(), Constants.ERROR_MUST_NOT_BE_EMPTY.formatted("createdAt"));
  }

  @Test
  @DisplayName("Verify that POST /users fail with 403 on a new user creation with invalid api-key")
  void createUserWithInvalidApiKeyTest() {
    User user = createUser("Tom");
    givenWithLogging()
            .contentType(ContentType.JSON)
            .header(Constants.X_API_KEY, "invalid")
            .body(user)
            .when()
            .post(Constants.POST_USER_ENDPOINT)
            .then()
            .log()
            .status()
            .log()
            .body()
            .statusCode(403)
            .body("error", equalTo(Constants.ERROR_INVALID_API_KEY));
  }

  private User createUser(String userName) {
    return User.builder().name(userName).job("AQA Engineer").build();
  }
}
