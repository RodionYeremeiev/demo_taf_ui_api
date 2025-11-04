import org.junit.jupiter.api.*;
import pages.InventoryPage;

public class LoginTests extends BaseUITest {

  InventoryPage inventoryPage = new InventoryPage();

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

  @Test
  @DisplayName("Logout from inventory with valid credentials redirects to login page")
  void logoutWithInvalidCredentials() {
    standardLogin();
    inventoryPage.clickBurgerMenu();
    inventoryPage.clickLogoutSideBarLink();
    loginPage.loginButtonShouldBeVisible();
  }
}
