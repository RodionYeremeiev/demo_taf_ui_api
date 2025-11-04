import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

public class CheckoutTests extends BaseUITest {

    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;

    @BeforeEach
    void setUp() {
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        confirmationPage = new ConfirmationPage();
    }

    @Test
    @DisplayName("Complete checkout flow and verify confirmation")
    public void completeCheckoutFlow() {
        standardLogin();
        inventoryPage.addFirstProductToCart();
        attachInventoryScreenshotAfterProductAdded();
        inventoryPage.goToCart();
        attachCartScreenshotAfterProductAdded();
        cartPage.proceedToCheckout();
        attachInitialCheckoutScreenshot();
        checkoutPage.fillInfo("John", "Doe", "01001");
        attachScreenshot("Checkout State after info filled");
        checkoutPage.continueCheckout();
        attachScreenshot("Checkout State after continue");
        checkoutPage.finishCheckout();
        verifyCheckoutSuccess();
    }

    @Test
    @DisplayName("Checkout with missing info shows error")
    public void checkoutMissingInfo() {
        standardLogin();
        inventoryPage.addFirstProductToCart();
        attachInventoryScreenshotAfterProductAdded();
        inventoryPage.goToCart();
        attachCartScreenshotAfterProductAdded();
        cartPage.proceedToCheckout();
        attachInitialCheckoutScreenshot();
        checkoutPage.continueCheckout();
        attachScreenshot("Checkout State after continue without information");
        verifyCheckoutFailed();
    }

    @Step("Verify checkout failed")
    public void verifyCheckoutFailed() {
    Assertions.assertTrue(
        checkoutPage.getErrorMessage().contains("Error: First Name is required"),
            "Error: expected error message was not displayed");
    }

    @Step("Verify checkout successfully completed")
    public void verifyCheckoutSuccess() {
        Assertions.assertTrue(
                confirmationPage.getConfirmationMessage().contains("Thank you for your order!"),
                "Error: confirmation failed");
    }
}