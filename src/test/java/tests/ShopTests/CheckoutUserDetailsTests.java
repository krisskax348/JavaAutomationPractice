package tests.ShopTests;

import actions.LoggedUserActions;
import actions.UnauthenticatedUserActions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.*;
import tests.BaseTest;

public class CheckoutUserDetailsTests extends BaseTest {
    private UnauthenticatedUserActions unauthenticatedUserActions;
    private LoggedUserActions userActions;

    @BeforeEach
    public void setup() {
        driverSetup();
        unauthenticatedUserActions = new UnauthenticatedUserActions(driver);
        userActions = new LoggedUserActions(driver);

        unauthenticatedUserActions.openPage(LoginPage.BASE_URL);
        unauthenticatedUserActions.login(LoginPage.USERNAME, LoginPage.PASSWORD);
    }

    @ParameterizedTest(name = "{index}: firstName=''{0}'' lastName=''{1}'' postalCode =''{2}''")
    @CsvSource({
            "Krisi, Teneva, '', Error: Postal Code is required",
            "Krisi, '', 6000, Error: Last Name is required",
            "'', Teneva, 6000, Error: First Name is required"
    })
    void verifyUnsuccessfulCheckoutWithWrongDetails(String firstName, String lastName, String postalCode, String expectedMessage) {
        userActions.addRandomItemToCart();
        userActions.clickOnButton(HomePage.SHOPPING_CART);

        userActions.clickOnButton(CartPage.CHECKOUT_BUTTON);
        userActions.enterText(UserDetailsPage.FIRST_NAME, firstName);
        userActions.enterText(UserDetailsPage.LAST_NAME, lastName);
        userActions.enterText(UserDetailsPage.POSTAL_CODE, postalCode);
        userActions.clickOnButton(UserDetailsPage.CONTINUE_BUTTON);

        String actualMessage = userActions.getMessage(UserDetailsPage.ERROR_MESSAGE_USER_DETAILS);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
