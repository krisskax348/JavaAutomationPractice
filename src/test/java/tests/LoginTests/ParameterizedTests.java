package tests.LoginTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;
import tests.BaseTest;

public class ParameterizedTests extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
    }

    @ParameterizedTest(name = "{index}: username=''{0}'' password=''{1}''")
    @CsvSource({
            "standard_user, scsc, Epic sadface: Username and password do not match any user in this service",
            "standard_userS, secret_sauce, Epic sadface: Username and password do not match any user in this service",
            "standard_userS, secret_sauceS, Epic sadface: Username and password do not match any user in this service",
            "'', secret_sauce, Epic sadface: Username is required",
            "standard_user, '', Epic sadface: Password is required"
    })
    void verifyUnsuccessfulLoginWithWrongCredentials(String username, String password, String expectedMessage) {
        loginPage.openPage();
        loginPage.userLogin(username, password);
        String actualMessage = loginPage.getWrongCredentialsMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
