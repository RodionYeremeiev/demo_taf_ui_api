import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FooterTests extends BaseUITest{

    @Test
    @DisplayName("Footer social media links present")
    public void footerLinksOpenCorrectPages() {
        standardLogin();
        loginPage.scrollToFooter();
        verifyTwitterFooterLogoIsDisplayed();
        verifyFacebookFooterLogoIsDisplayed();
        verifyLinkedInFooterLogoIsDisplayed();
    }

    @Step("Verify footer contains Twitter logo for redirect")
    public void verifyTwitterFooterLogoIsDisplayed() {
        Assertions.assertTrue(loginPage.isTwitterLogoDisplayed(), "Twitter logo is not displayed");
    }

    @Step("Verify footer contains Facebook logo for redirect")
    public void verifyFacebookFooterLogoIsDisplayed() {
        Assertions.assertTrue(loginPage.isFaceBookLogoDisplayed(), "Facebook logo is not displayed");
    }

    @Step("Verify footer contains LinkedIn logo for redirect")
    public void verifyLinkedInFooterLogoIsDisplayed() {
        Assertions.assertTrue(loginPage.isLinkedInLogoDisplayed(), "LinkedIn logo is not displayed");
    }
}
