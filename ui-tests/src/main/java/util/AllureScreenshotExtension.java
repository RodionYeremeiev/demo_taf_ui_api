package util;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.codeborne.selenide.WebDriverRunner;

import java.nio.charset.StandardCharsets;

public class AllureScreenshotExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        boolean testFailed = context.getExecutionException().isPresent();
        if (testFailed) {
            try {
                byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                Allure.getLifecycle().addAttachment("Failure Screenshot", "image/png", "png", screenshot);

                String pageSource = WebDriverRunner.source();
                Allure.getLifecycle().addAttachment("Page Source", "text/html", "html",
                        pageSource.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }
}
