package tests.LoginTests;

import actions.UnauthenticatedUserActions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;
import tests.BaseTest;

public class ParameterizedTests extends BaseTest {
    private UnauthenticatedUserActions unauthenticatedUserActions;

    @BeforeEach
    public void setup() {
        driverSetup();
        unauthenticatedUserActions = new UnauthenticatedUserActions(driver);
        unauthenticatedUserActions.openPage(LoginPage.BASE_URL);
    }

    @ParameterizedTest(name = "{index}: username=''{0}'' password=''{1}''")
    @CsvSource({
            "incorrect_user, secret_sauce, Epic sadface: Username and password do not match any user in this service",
            "standard_userS, incorrect_password, Epic sadface: Username and password do not match any user in this service",
            "'', secret_sauce, Epic sadface: Username is required",
            "standard_user, '', Epic sadface: Password is required"
    })
    void verifyUnsuccessfulLoginWithWrongCredentials(String username, String password, String expectedMessage) {
        unauthenticatedUserActions.login(username, password);
        String actualMessage = unauthenticatedUserActions.getMessage(LoginPage.ERROR_MESSAGE_LOGIN);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
