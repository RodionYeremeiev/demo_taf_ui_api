import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.InventoryPage;

public class CartTests extends BaseUITest {
    
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeEach
    void setUp() {
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
    }

    @Test
    @DisplayName("Add product to cart and verify cart badge")
    void addProductToCart() {
        standardLogin();
        inventoryPage.addFirstProductToCart();
        attachInventoryScreenshotAfterProductAdded();
        verifyCartBadgeNumberDisplayed();
        inventoryPage.goToCart();
        verifyCartItemAdded();
    }

    @Test
    @DisplayName("Remove product from cart and verify cart is empty")
    void removeProductFromCart() {
        standardLogin();
        inventoryPage.addFirstProductToCart();
        attachInventoryScreenshotAfterProductAdded();
        inventoryPage.goToCart();
        attachCartScreenshotAfterProductAdded();
        verifyCartItemAdded();
        cartPage.removeItem();
        verifyCartItemRemoved();
        cartBadgeNumberDisappear();
    }

    @Step("Cart badge should display product count {expectedCount}")
    private void verifyCartBadgeNumberDisplayed() {
        Assertions.assertTrue(
                inventoryPage.isCartBadgeCountDisplayed("1"),
                "Error: Cart badge count is not displayed");
    }

    @Step("Verify Cart item added to cart")
    private void verifyCartItemAdded() {
        Assertions.assertTrue(
                cartPage.isFirstCartItemDisplayed(),
                "Error: Cart item is not displayed");
    }

    @Step("Verify Cart item removed to cart")
    private void verifyCartItemRemoved() {
        Assertions.assertFalse(
                cartPage.isFirstCartItemDisplayed(),
                "Error: Cart item is still displayed");
    }

    @Step("Verify Cart counter longer visible")
    private void cartBadgeNumberDisappear() {
        Assertions.assertFalse(cartPage.isCartBadgeCountDisplayed());
    }
}
