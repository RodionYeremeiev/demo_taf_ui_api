package pages;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
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

  @Step("Scroll to footer")
  public void scrollToFooter() {
    pageFooter.scrollTo();
  }

  public boolean isElementDisplayed(SelenideElement element) {
    return element.isDisplayed();
  }

  public boolean isTwitterLogoDisplayed(){
    return isElementDisplayed(twitterFooterLogo);
  }

  public boolean isFaceBookLogoDisplayed(){
    return isElementDisplayed(facebookFooterLogo);
  }

  public boolean isLinkedInLogoDisplayed(){
    return isElementDisplayed(linkedinFooterLogo);
  }

  public boolean isLoginButtonDisplayed() {
    return loginButton.isDisplayed();
  }

  public boolean isErrorMessageDisplayed(String message) {
    return errorMessage.isDisplayed() && errorMessage.is(Condition.text(message));
  }

  public boolean isInventoryContainerVisible() {
    return inventoryContainer.isDisplayed();
  }
}