package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    public ElementsCollection cartItems = $$(".cart_item");
    public SelenideElement removeButton = $(".cart_item .btn_secondary");
    public SelenideElement checkoutButton = $("#checkout");
    public SelenideElement cartBadge = $(".shopping_cart_badge");

    public void removeItem() {
        removeButton.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void cartBadgeNumberShouldDisappear(){
        cartBadge.shouldNotBe(Condition.visible);
    }
}
