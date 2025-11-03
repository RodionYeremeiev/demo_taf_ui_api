import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTests {
    LoginPage loginPage = new LoginPage();
    InventoryPage inventoryPage = new InventoryPage();

    @Test
    public void sortByPriceLowToHigh() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        verifyProductPrice("$29.99");
        inventoryPage.sortByPriceLowToHigh();
        verifyProductPrice("$7.99");
    }

    @Test
    public void inventoryLoadsAllProducts() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.shouldDisplayAllProducts(6);
    }

    @Step("Verify product price is {expected}")
    private void verifyProductPrice(String expected) {
        Assertions.assertEquals(expected, inventoryPage.getFirstProductPrice());
    }

}
