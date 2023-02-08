package tests.LoginTests;

import actions.UnauthenticatedUserActions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import tests.BaseTest;

public class LoginTests extends BaseTest {
    private UnauthenticatedUserActions unauthenticatedUserActions;


    @BeforeEach
    public void setup() {
        driverSetup();
        unauthenticatedUserActions = new UnauthenticatedUserActions(driver);
        unauthenticatedUserActions.openPage(LoginPage.BASE_URL);

    }

    @Test
    public void verifySuccessfulUserLogin() {
        unauthenticatedUserActions.login(LoginPage.USERNAME, LoginPage.PASSWORD);
        String actualTitle = unauthenticatedUserActions.getMessage(LoginPage.PRODUCTS_TITLE);
        String expectedTitle = "PRODUCTS";
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void verifyUnsuccessfulLogin() {
        unauthenticatedUserActions.enterText(LoginPage.USERNAME_FIELD,"standard_userS");
        unauthenticatedUserActions.enterText(LoginPage.PASS_FIELD,LoginPage.PASSWORD);
        unauthenticatedUserActions.clickOnButton(LoginPage.LOGIN_BUTTON);
        String errorMsg = unauthenticatedUserActions.getMessage(LoginPage.ERROR_MESSAGE_LOGIN);
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMsg);
    }
}
