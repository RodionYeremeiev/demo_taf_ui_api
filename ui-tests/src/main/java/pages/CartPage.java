package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class CartPage {

    private final ElementsCollection cartItems = $$(".cart_item");
    private final SelenideElement removeButton = $(".cart_item .btn_secondary");
    private final SelenideElement checkoutButton = $("#checkout");
    private final SelenideElement cartBadge = $(".shopping_cart_badge");

    @Step("Remove item from cart")
    public void removeItem() {
        removeButton.click();
    }

    @Step("Proceed to checkout")
    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public boolean isFirstCartItemDisplayed() {
        return cartItems.first().isDisplayed();
    }

    public boolean isCartBadgeCountDisplayed() {
        return cartBadge.isDisplayed();
    }
}
