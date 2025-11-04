package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    public ElementsCollection cartItems = $$(".cart_item");
    public SelenideElement removeButton = $(".cart_item .btn_secondary");
    public SelenideElement checkoutButton = $("#checkout");
    public SelenideElement cartBadge = $(".shopping_cart_badge");

    @Step("Remove item from cart")
    public void removeItem() {
        removeButton.click();
    }

    @Step("Proceed to checkout")
    public void proceedToCheckout() {
        checkoutButton.click();
    }
    
    @Step("Cart icon item counter should disappear")
    public void cartBadgeNumberShouldDisappear() {
        cartBadge.shouldNotBe(Condition.visible);
    }
}
