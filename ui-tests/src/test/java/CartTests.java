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
    void addProductToCart() {
        standardLogin();
        inventoryPage.addFirstProductToCart();
        inventoryPage.shouldSeeCartBadge("1");
        inventoryPage.goToCart();
        cartPage.cartItems.first().shouldBe(visible);
    }

    @Test
    void removeProductFromCart() {
        standardLogin();
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();
        cartPage.removeItem();
        cartPage.cartItems.shouldHave(CollectionCondition.size(0));
        cartPage.cartBadgeNumberShouldDisappear();
    }
    
}

