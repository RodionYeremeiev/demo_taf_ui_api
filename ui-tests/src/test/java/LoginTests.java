import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import pages.LoginPage;

public class LoginTests extends BaseUITest {

  LoginPage loginPage;

  @BeforeEach
  void setUp() {
    loginPage = new LoginPage();
    loginPage.openPage();
  }

  @Test
  void loginWithValidCredentials() {
    loginPage.login("standard_user", "secret_sauce");
    loginPage.shouldBeLoggedIn();
  }

  @Test
  void loginWithInvalidCredentials() {
    loginPage.login("locked_out_user", "wrong_password");
    loginPage.shouldSeeError("Username and password do not match");
  }

  @AfterEach
  void tearDown() {
    Selenide.closeWebDriver();
  }
}
