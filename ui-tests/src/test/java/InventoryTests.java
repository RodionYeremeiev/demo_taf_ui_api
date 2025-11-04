import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;

public class InventoryTests extends BaseUITest{

    InventoryPage inventoryPage = new InventoryPage();

    @Test
    @DisplayName("Sort products by price low to high")
    public void sortByPriceLowToHigh() {
        standardLogin();
        attachScreenshot("Inventory before sorting");
        verifyProductPrice("$29.99");
        inventoryPage.sortByPriceLowToHigh();
        verifyProductPrice("$7.99");
    }

    @Test
    @DisplayName("Inventory page loads all products")
    public void inventoryLoadsAllProducts() {
        standardLogin();
        shouldDisplayAllProducts(6);
    }

    @Step("Verify product price is {expected}")
    private void verifyProductPrice(String expected) {
        Assertions.assertEquals(expected, inventoryPage.getFirstProductPrice());
    }

    @Step("Product list should display all {expectedCount} products")
    public void shouldDisplayAllProducts(int expectedCount) {
        Assertions.assertTrue(
                inventoryPage.isAllProductsDisplayed(expectedCount),
                "Error: All products should be displayed");
    }
}
