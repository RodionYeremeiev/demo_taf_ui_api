import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import static com.codeborne.selenide.Condition.*;

public class CartTests {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
    }

    @Test
    void addProductToCart() {
        inventoryPage.addFirstProductToCart();
        inventoryPage.shouldSeeCartBadge("1");
        inventoryPage.goToCart();
        cartPage.cartItems.first().shouldBe(visible);
    }

    @Test
    void removeProductFromCart() {
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();
        cartPage.removeItem();
        cartPage.cartItems.shouldHave(CollectionCondition.size(0));
        cartPage.cartBadgeNumberShouldDisappear();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}

