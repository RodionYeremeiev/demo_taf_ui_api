package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ConfirmationPage {
    public SelenideElement confirmationMessage = $(".complete-header");

    public String getConfirmationMessage() {
        confirmationMessage.shouldBe(Condition.visible);
        return confirmationMessage.getText();
    }
}
