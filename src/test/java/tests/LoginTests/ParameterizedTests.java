package tests.LoginTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.LoginPage;
import tests.BaseTest;

public class ParameterizedTests extends BaseTest {
    private LoginPage loginPage;
    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
    }
    @ParameterizedTest(name = "{index}: username=''{0}''")
    @ValueSource(strings = {"standard_user", "problem_user", "standard_user"})
    public void verifySuccessfulUserLogin(String username) {
        loginPage.openPage();
        loginPage.userLogin(username, LoginPage.PASSWORD);
        String actualTitle = loginPage.getProductsTitle();
        String expectedTitle = "Products".toUpperCase();
        Assertions.assertEquals(expectedTitle, actualTitle);

    }
    @ParameterizedTest
    @ValueSource(strings = {"standard_userS", "user", "test345"})
    public void verifyUnsuccessfulLoginWithWrongUsername(String username){
        loginPage.openPage();
        loginPage.userLogin(username, LoginPage.PASSWORD);
        String errorMsg = loginPage.getWrongCredentialsMessage();
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMsg);
    }
    @ParameterizedTest
    @ValueSource(strings = {"secret_sauce", "scsc", "sauce"})
    public void verifyUnsuccessfulLoginWithWrongPassword(String password){
        loginPage.openPage();
        loginPage.userLogin(LoginPage.USERNAME, password);
        String errorMsg = loginPage.getWrongCredentialsMessage();
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMsg);
    }
}
