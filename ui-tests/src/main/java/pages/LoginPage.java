package pages;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.util.stream.Stream;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginPage {

  private final SelenideElement usernameInput = $("#user-name");
  private final SelenideElement passwordInput = $("#password");
  private final SelenideElement loginButton = $("#login-button");
  private final SelenideElement errorMessage = $(".error-message-container");
  private final SelenideElement inventoryContainer = $("#inventory_container");
  private final SelenideElement pageFooter = $(".footer");
  private final SelenideElement twitterFooterLogo = $("[href='https://twitter.com/saucelabs']");
  private final SelenideElement facebookFooterLogo = $("[href='https://www.facebook.com/saucelabs']");
  private final SelenideElement linkedinFooterLogo = $("[href='https://www.linkedin.com/company/sauce-labs/']");

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
    errorMessage.shouldHave(Condition.text(message));
  }

  @Step("Should login successfully")
  public void shouldBeLoggedIn() {
    inventoryContainer.shouldBe(Condition.visible);
  }

  @Step("Scroll to footer")
  public void scrollToFooter() {
    pageFooter.scrollTo();
  }

  @Step("Verify footer contains {logoName} logo for redirect")
  public void verifyFooterLogoElement(String logoName) {
    SelenideElement elementToCheck = getFooterElementByName(logoName);
    elementToCheck.shouldBe(Condition.visible);
  }

  public void verifyFooterLogoElement() {
    Stream.of("facebook", "linkedin", "twitter").forEach(this::verifyFooterLogoElement);
  }

  private SelenideElement getFooterElementByName(String logoName) {
    return switch (logoName.toLowerCase()) {
      case "facebook" -> facebookFooterLogo;
      case "twitter" -> twitterFooterLogo;
      case "linkedin" -> linkedinFooterLogo;
      default -> throw new IllegalStateException("Unexpected value: " + logoName.toLowerCase());
    };
  }
}