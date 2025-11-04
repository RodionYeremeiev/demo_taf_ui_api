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
        inventoryPage.goToCart();
        cartPage.proceedToCheckout();
        checkoutPage.fillInfo("John", "Doe", "01001");
        checkoutPage.continueCheckout();
        checkoutPage.finishCheckout();
        confirmationPage.verifyCheckoutSuccess();
    }

    @Test
    @DisplayName("Checkout with missing info shows error")
    public void checkoutMissingInfo() {
        standardLogin();
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();
        cartPage.proceedToCheckout();
        checkoutPage.continueCheckout();
        checkoutPage.verifyCheckoutFailed();
    }
}