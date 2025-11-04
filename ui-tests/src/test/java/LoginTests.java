import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseUITest {

  InventoryPage inventoryPage = new InventoryPage();

  @Test
  @DisplayName("Login with valid credentials redirects to inventory page")
  void loginWithValidCredentials() {
    standardLogin();
    shouldBeLoggedIn();
  }

  @Test
  @DisplayName("Login with invalid credentials shows error message")
  void loginWithInvalidCredentials() {
    lockedLogin();
    shouldSeeErrorMessage("Username and password do not match");
  }

  @Test
  @DisplayName("Logout from inventory with valid credentials redirects to login page")
  void logoutWithInvalidCredentials() {
    standardLogin();
    inventoryPage.clickBurgerMenu();
    attachScreenshot("State after burger menu clicked");
    inventoryPage.clickLogoutSideBarLink();
    loginButtonShouldBeVisible();
  }

  @Step("Should login successfully")
  public void shouldBeLoggedIn() {
    assertTrue(
            loginPage.isInventoryContainerVisible(),
            "Error: Inventory container is not visible");
  }

  @Step("Should display login failed error")
  public void shouldSeeErrorMessage(String message) {
    assertTrue(
            loginPage.isErrorMessageDisplayed(message),
            "Error message should be displayed");
  }

  @Step("Verify login button is displayed")
  public void loginButtonShouldBeVisible() {
    assertTrue(
            loginPage.isLoginButtonDisplayed(),
            "Login button should be visible");
  }
}
