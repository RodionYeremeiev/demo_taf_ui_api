import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.CollectionCondition;
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
        inventoryPage.shouldSeeCartBadge("1");
        inventoryPage.goToCart();
        cartPage.cartItems.first().shouldBe(visible);
    }

    @Test
    @DisplayName("Remove product from cart and verify cart is empty")
    void removeProductFromCart() {
        standardLogin();
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();
        cartPage.removeItem();
        cartPage.cartItems.shouldHave(CollectionCondition.size(0));
        cartPage.cartBadgeNumberShouldDisappear();
    }
}
