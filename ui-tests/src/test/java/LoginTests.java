import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

public class LoginTests extends BaseUITest {

  @Test
  @DisplayName("Login with valid credentials redirects to inventory page")
  void loginWithValidCredentials() {
    standardLogin();
    loginPage.shouldBeLoggedIn();
  }

  @Test
  @DisplayName("Login with invalid credentials shows error message")
  void loginWithInvalidCredentials() {
    lockedLogin();
    loginPage.shouldSeeError("Username and password do not match");
  }

}
