package tests.ShopTests;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;
import tests.BaseTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSuccessfulOrder extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private UserDetailsPage userDetailsPage;
    private FinalizingOrderPage finalizingOrderPage;
    private CompleteOrderPage completeOrderPage;

    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        userDetailsPage = new UserDetailsPage(driver);
        finalizingOrderPage = new FinalizingOrderPage(driver);
        completeOrderPage = new CompleteOrderPage(driver);
    }
    @Test
    public void verifySuccessfulOrder(){
        loginPage.openPage();
        loginPage.userLogin("standard_user", "secret_sauce");
        homePage.applyFilter();
        //String price = homePage.chooseItemByValue("49.99");
        String price1 = homePage.chooseItemByValue("29.99");


        homePage.viewCart();
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class=\"item_pricebar\"]"));


        cartPage.proceedToCheckout();

        userDetailsPage.enterUserDetails("Krisi", "Teneva", "6000");
        finalizingOrderPage.finishOrder();

        String actual = completeOrderPage.getConfirmationText();
        Assertions.assertEquals("THANK YOU FOR YOUR ORDER", actual);
    }
}
