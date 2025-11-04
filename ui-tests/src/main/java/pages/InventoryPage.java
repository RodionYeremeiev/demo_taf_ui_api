package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InventoryPage {

    private final ElementsCollection productList = Selenide.$$(".inventory_item");
    private final SelenideElement sortDropdown = $(".product_sort_container");
    private final SelenideElement cartBadge = $(".shopping_cart_badge");
    private final SelenideElement cartIcon = $(".shopping_cart_link");
    private final SelenideElement burgerMenu = $("#react-burger-menu-btn");
    private final SelenideElement logoutSideBarLink = $("#logout_sidebar_link");

    @Step("sort by {optionText} text")
    public void sortBy(String optionText) {
        sortDropdown.selectOption(optionText);
    }

    @Step("Add first product from inventory to cart")
    public void addFirstProductToCart() {
        productList.first().$("button").click();
    }

    @Step("open cart")
    public void goToCart() {
        cartIcon.click();
    }

    public boolean isAllProductsDisplayed(int expectedCount) {
        productList.shouldHave(CollectionCondition.size(6));
        return productList.size() == expectedCount;
    }

    @Step("Click burger-menu")
    public void clickBurgerMenu() {
        burgerMenu.click();
    }

    @Step("Click logout")
    public void clickLogoutSideBarLink() {
        logoutSideBarLink.shouldBe(Condition.visible);
        logoutSideBarLink.click();
    }

    public boolean isCartBadgeCountDisplayed(String expectedCount) {
        cartBadge.shouldBe(Condition.visible);
        return cartBadge.text().equals(expectedCount);
    }

    public void sortByPriceLowToHigh() {
        sortBy("Price (low to high)");
    }

    public String getFirstProductPrice() {
        return productList.first().$(".inventory_item_price").getText();
    }
}

