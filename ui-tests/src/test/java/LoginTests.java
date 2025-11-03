import org.junit.jupiter.api.*;

public class LoginTests extends BaseUITest {

  @Test
  void loginWithValidCredentials() {
    standardLogin();
    loginPage.shouldBeLoggedIn();
  }

  @Test
  void loginWithInvalidCredentials() {
    lockedLogin();
    loginPage.shouldSeeError("Username and password do not match");
  }

}
