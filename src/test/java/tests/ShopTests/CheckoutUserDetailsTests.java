package tests.ShopTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.*;
import tests.BaseTest;

public class CheckoutUserDetailsTests extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private UserDetailsPage userDetailsPage;

    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        userDetailsPage = new UserDetailsPage(driver);
        loginPage.openPage();
        loginPage.userLogin(LoginPage.USERNAME, LoginPage.PASSWORD);
    }

    @ParameterizedTest(name = "{index}: firstName=''{0}'' lastName=''{1}'' postalCode =''{2}''")
    @CsvSource({
            "Krisi, Teneva, '', Error: Postal Code is required",
            "Krisi, '', 6000, Error: Last Name is required",
            "'', Teneva, 6000, Error: First Name is required"
    })
    void verifyUnsuccessfulCheckoutWithWrongDetails(String firstName, String lastName, String postalCode, String expectedMessage) {
        homePage.addRandomItemToCart();
        homePage.viewCart();

        cartPage.proceedToCheckout();
        userDetailsPage.enterUserDetails(firstName, lastName, postalCode);

        String actualMessage = userDetailsPage.getWrongUserDetailsMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
