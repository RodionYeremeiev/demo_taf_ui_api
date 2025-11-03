import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseUITest {

  @BeforeAll
  public static void globalSetup() {
    Configuration.browser = "chrome";
    Configuration.headless = false;
    Configuration.browserSize = "1920x1080";
    Configuration.timeout = 5000;
    Configuration.pageLoadStrategy = "normal"; // or "eager", "none"
    Configuration.screenshots = true;
    Configuration.reportsFolder = "reports/screenshots";
    Configuration.webdriverLogsEnabled = true;
    SelenideLogger.addListener("allure", new AllureSelenide());
  }
}
