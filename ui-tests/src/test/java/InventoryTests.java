import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;

public class InventoryTests extends BaseUITest{

    InventoryPage inventoryPage = new InventoryPage();

    @Test
    public void sortByPriceLowToHigh() {
        standardLogin();
        verifyProductPrice("$29.99");
        inventoryPage.sortByPriceLowToHigh();
        verifyProductPrice("$7.99");
    }

    @Test
    public void inventoryLoadsAllProducts() {
        standardLogin();
        inventoryPage.shouldDisplayAllProducts(6);
    }

    @Step("Verify product price is {expected}")
    private void verifyProductPrice(String expected) {
        Assertions.assertEquals(expected, inventoryPage.getFirstProductPrice());
    }

}
