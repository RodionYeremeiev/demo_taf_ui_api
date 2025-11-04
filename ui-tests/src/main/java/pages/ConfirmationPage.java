package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ConfirmationPage {
    public SelenideElement confirmationMessage = $(".complete-header");

    @Step("Verify checkout successfully completed")
    public void verifyCheckoutSuccess() {
        confirmationMessage.shouldBe(Condition.visible);
        confirmationMessage.shouldHave(Condition.text("THANK YOU FOR YOUR ORDER"));
    }

    public String getMessage() {
        return confirmationMessage.getText();
    }
}
