package tests.LoginTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import tests.BaseTest;

public class LoginTests extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
        loginPage.openPage();
    }

    @Test
    public void verifySuccessfulUserLogin() {
        loginPage.userLogin(LoginPage.USERNAME, LoginPage.PASSWORD);
        String actualTitle = loginPage.getProductsTitle();
        String expectedTitle = "PRODUCTS";
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void verifyUnsuccessfulLogin() {
        loginPage.userLogin("standard_userS", LoginPage.PASSWORD);
        String errorMsg = loginPage.getWrongCredentialsMessage();
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMsg);
    }
}
