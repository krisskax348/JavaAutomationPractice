package tests.LoginTests;

import constants.Constants;
import pages.LoginPage;
import tests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnsuccessfulLoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
    }
    @Test
    public void verifyUnsuccessfulLogin(){
        loginPage.openPage();
        loginPage.userLogin("standard_userS", Constants.PASSWORD);
        String errorMsg = loginPage.getWrongCredentialsMessage();
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMsg);
    }
}
