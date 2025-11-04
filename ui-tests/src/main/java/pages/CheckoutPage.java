package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    public SelenideElement firstName = $("#first-name");
    public SelenideElement lastName = $("#last-name");
    public SelenideElement zipCode = $("#postal-code");
    public SelenideElement continueButton = $("#continue");
    public SelenideElement finishButton = $("#finish");
    public SelenideElement errorMessage = $(".error-message-container");

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

    @Step("Verify checkout failed")
    public void verifyCheckoutFailed() {
        errorMessage.shouldBe(Condition.visible);
        errorMessage.shouldHave(Condition.text("Error: First Name is required"));
    }
}
