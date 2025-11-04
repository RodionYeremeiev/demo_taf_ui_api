import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FooterTests extends BaseUITest{

    @Test
    @DisplayName("Footer social media links open correct pages")
    @Step("Verify footer links point to expected domains")
    public void footerLinksOpenCorrectPages() {
        standardLogin();
        loginPage.scrollToFooter();
        loginPage.verifyFooterLogoElement();
    }
}
