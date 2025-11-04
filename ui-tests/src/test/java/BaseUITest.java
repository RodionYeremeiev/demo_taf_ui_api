import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.LoginPage;

public class BaseUITest {

  LoginPage loginPage;

  @BeforeAll
  public static void globalSetup() {
    Configuration.browser = "chrome";
    Configuration.headless = Boolean.parseBoolean(System.getProperty("headless"));
    Configuration.browserSize = "1920x1080";
    Configuration.timeout = 5000;
    Configuration.pageLoadStrategy = "normal"; // or "eager", "none"
    Configuration.screenshots = true;
    Configuration.reportsFolder = "reports/screenshots";
    Configuration.webdriverLogsEnabled = true;
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  public void login(String username, String password) {
    loginPage = new LoginPage();
    loginPage.openPage();
    loginPage.login(username, password);
  }

  @Step("Login as standard_user user")
  public void standardLogin() {
    login("standard_user", "secret_sauce");
  }

  @Step("Login as locked_out_user user")
  public void lockedLogin() {
    login("locked_out_user", "wrong_password");
  }

  @AfterEach
  @Step("Teardown and capture final screenshot")
  public void tearDown() {
    attachScreenshot("Final state before closing");
    Selenide.closeWebDriver();
  }

  @Step("Attach {name} screenshot")
  protected void attachScreenshot(String name) {
    byte[] screenshot =
        ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    Allure.getLifecycle().addAttachment(name, "image/png", "png", screenshot);
  }
}
