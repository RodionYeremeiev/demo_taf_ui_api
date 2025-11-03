package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import lombok.NoArgsConstructor;

import static com.codeborne.selenide.Selenide.$;

@NoArgsConstructor
public class InventoryPage {

    private final ElementsCollection productList = Selenide.$$(".inventory_item");

    private final SelenideElement sortDropdown = $(".product_sort_container");

    private final SelenideElement cartBadge = $(".shopping_cart_badge");

    private final SelenideElement cartIcon = $(".shopping_cart_link");

    public static final String BTN_INVENTORY = ".btn_inventory";

    @Step("sort by {optionText} text")
    public void sortBy(String optionText) {
        sortDropdown.selectOption(optionText);
    }

    @Step("Add first product from inventory to cart")
    public void addFirstProductToCart() {
        productList.first().$("button").click();
    }

    public void sortByPriceLowToHigh() {
        sortBy("Price (low to high)");
    }

    public String getFirstProductPrice() {
        return productList.first().$(".inventory_item_price").getText();
    }

    public int getProductCount() {
        return productList.size();
    }


    @Step("open cart")
    public void goToCart() {
        cartIcon.click();
    }

    @Step("Cart badge should display product count {count}")
    public void shouldSeeCartBadge(String count) {
        cartBadge.shouldHave(com.codeborne.selenide.Condition.text(count));
    }

    @Step("Product list should display all {expectedCount} products")
    public void shouldDisplayAllProducts(int expectedCount) {
        productList.shouldHave(CollectionCondition.size(expectedCount));
    }
}

