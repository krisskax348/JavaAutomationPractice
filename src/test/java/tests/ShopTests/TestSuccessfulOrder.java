package tests.ShopTests;

import models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

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
        List<Item> expectedProducts = new ArrayList<>();

        //expectedProducts.addAll(homePage.chooseItemByValue("29.99"));
        //expectedProducts.addAll(homePage.chooseItemByValue("49.99"));
        expectedProducts.addAll(homePage.chooseItemByValue("15.99"));



        homePage.viewCart();
        List<Item> actualProducts = cartPage.getItemsInCart();
        Assertions.assertEquals(expectedProducts,actualProducts);



        cartPage.proceedToCheckout();

        userDetailsPage.enterUserDetails("Krisi", "Teneva", "6000");
        finalizingOrderPage.finishOrder();

        String actual = completeOrderPage.getConfirmationText();
        Assertions.assertEquals("THANK YOU FOR YOUR ORDER", actual);
    }
}
