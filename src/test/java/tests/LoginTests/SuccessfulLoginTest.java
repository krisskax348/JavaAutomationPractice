package tests.LoginTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import tests.BaseTest;

public class SuccessfulLoginTest extends BaseTest {
        private LoginPage loginPage;

        @BeforeEach
        public void setup() {
                driverSetup();
                loginPage = new LoginPage(driver);
        }
        @Test
        public void verifyUserLogin() {

                loginPage.openPage();

                loginPage.userLogin("standard_user", "secret_sauce");

                String actualTitle = loginPage.getProductsTitle();
                String expectedTitle = "Products".toUpperCase();
                Assertions.assertEquals(expectedTitle, actualTitle);
        }
}
