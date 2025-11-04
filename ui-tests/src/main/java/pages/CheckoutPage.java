package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    private final SelenideElement firstName = $("#first-name");
    private final SelenideElement lastName = $("#last-name");
    private final SelenideElement zipCode = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");
    private final SelenideElement finishButton = $("#finish");
    private final SelenideElement errorMessage = $(".error-message-container");

    @Step("Fill checkout info")
    public void fillInfo(String first, String last, String zip) {
        firstName.setValue(first);
        lastName.setValue(last);
        zipCode.setValue(zip);
    }

    @Step("Continue checkout")
    public void continueCheckout() {
        continueButton.click();
    }

    @Step("Finish checkout")
    public void finishCheckout() {
        finishButton.click();
    }

    public String getErrorMessage() {
        errorMessage.shouldBe(Condition.visible);
        errorMessage.shouldHave(Condition.text("Error: First Name is required"));
        return errorMessage.getText();
    }
}
