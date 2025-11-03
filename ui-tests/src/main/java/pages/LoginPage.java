package pages;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginPage {

  private final SelenideElement usernameInput = $("#user-name");

  private final SelenideElement passwordInput = $("#password");

  private final SelenideElement loginButton = $("#login-button");

  private final SelenideElement errorMessage = $(".error-message-container");

  private final SelenideElement inventoryContainer = $("#inventory_container");

  @Step("Opening SauceDemo")
  public void openPage() {
    open("https://www.saucedemo.com/");
  }

  @Step("Login to Sauce Demo")
  public void login(String username, String password) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    loginButton.click();
  }

  @Step("Should display login failed error")
  public void shouldSeeError(String message) {
    errorMessage.shouldHave(com.codeborne.selenide.Condition.text(message));
  }

  @Step("Should login successfully")
  public void shouldBeLoggedIn() {
    inventoryContainer.shouldBe(com.codeborne.selenide.Condition.visible);
  }
}