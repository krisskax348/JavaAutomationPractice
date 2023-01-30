package tests.ShopTests;

import com.endava.models.Item;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class VerifyShipmentInfoTests extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private UserDetailsPage userDetailsPage;
    private FinalizingOrderPage finalizingOrderPage;


    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        userDetailsPage = new UserDetailsPage(driver);
        finalizingOrderPage = new FinalizingOrderPage(driver);
        loginPage.openPage();
        loginPage.userLogin(LoginPage.USERNAME, LoginPage.PASSWORD);
    }

    @Test
    public void emptyOrderTest() {
        List<Item> expectedItems = new ArrayList<>();
        homePage.viewCart();
        List<Item> actualCartItems = cartPage.getItemsInCart();
        Assertions.assertEquals(expectedItems, actualCartItems);

        cartPage.proceedToCheckout();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().firstName();
        String postalCode = faker.address().zipCode();
        userDetailsPage.enterUserDetails(firstName, lastName, postalCode);

        String actualPaymentInfo = finalizingOrderPage.getPaymentInfo();
        String actualShippingInfo = finalizingOrderPage.getShippingInfo();
        double actualSubtotalAmount = finalizingOrderPage.getSubtotalAmount();
        double actualTaxAmount = finalizingOrderPage.getTaxAmount();
        double actualTotalAmount = finalizingOrderPage.getFinalAmount();


        Assertions.assertEquals("SauceCard #31337", actualPaymentInfo);
        Assertions.assertEquals("FREE PONY EXPRESS DELIVERY!", actualShippingInfo);
        Assertions.assertEquals(0, actualSubtotalAmount);
        Assertions.assertEquals(0.00, actualTaxAmount);
        Assertions.assertEquals(0.00, actualTotalAmount);


    }

    @Test
    public void oneItemOrderTest() {
        List<Item> expectedItems = new ArrayList<>();
        Item item1 = homePage.addRandomItemToCart();
        expectedItems.add(item1);

        double expectedSubtotalAmount = item1.getPrice();
        double expectedTax = Math.round((8 * expectedSubtotalAmount / 100) * 100.0) / 100.0;
        double expectedTotalAmount = expectedSubtotalAmount + expectedTax;

        homePage.viewCart();
        List<Item> actualCartItems = cartPage.getItemsInCart();
        Assertions.assertEquals(expectedItems, actualCartItems);

        cartPage.proceedToCheckout();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().firstName();
        String postalCode = faker.address().zipCode();
        userDetailsPage.enterUserDetails(firstName, lastName, postalCode);

        String actualPaymentInfo = finalizingOrderPage.getPaymentInfo();
        String actualShippingInfo = finalizingOrderPage.getShippingInfo();
        double actualSubtotalAmount = finalizingOrderPage.getSubtotalAmount();
        double actualTaxAmount = finalizingOrderPage.getTaxAmount();
        double actualTotalAmount = finalizingOrderPage.getFinalAmount();

        Assertions.assertEquals("SauceCard #31337", actualPaymentInfo);
        Assertions.assertEquals("FREE PONY EXPRESS DELIVERY!", actualShippingInfo);
        Assertions.assertEquals(expectedSubtotalAmount, actualSubtotalAmount);
        Assertions.assertEquals(expectedTax, actualTaxAmount);
        Assertions.assertEquals(expectedTotalAmount, actualTotalAmount);

    }

    @Test
    public void twoItemsOrderTest() {
        List<Item> expectedItems = new ArrayList<>();
        Item item1 = homePage.addRandomItemToCart();
        Item item2 = homePage.addRandomItemToCart();
        expectedItems.add(item1);
        expectedItems.add(item2);

        double expectedSubtotalAmount = item1.getPrice() + item2.getPrice();
        double expectedTax = Math.round((8 * expectedSubtotalAmount / 100) * 100.0) / 100.0;
        double expectedTotalAmount = expectedSubtotalAmount + expectedTax;

        homePage.viewCart();
        List<Item> actualCartItems = cartPage.getItemsInCart();
        Assertions.assertEquals(expectedItems, actualCartItems);

        cartPage.proceedToCheckout();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().firstName();
        String postalCode = faker.address().zipCode();
        userDetailsPage.enterUserDetails(firstName, lastName, postalCode);

        String actualPaymentInfo = finalizingOrderPage.getPaymentInfo();
        String actualShippingInfo = finalizingOrderPage.getShippingInfo();
        double actualSubtotalAmount = finalizingOrderPage.getSubtotalAmount();
        double actualTaxAmount = finalizingOrderPage.getTaxAmount();
        double actualTotalAmount = finalizingOrderPage.getFinalAmount();

        Assertions.assertEquals("SauceCard #31337", actualPaymentInfo);
        Assertions.assertEquals("FREE PONY EXPRESS DELIVERY!", actualShippingInfo);
        Assertions.assertEquals(expectedSubtotalAmount, actualSubtotalAmount);
        Assertions.assertEquals(expectedTax, actualTaxAmount);
        Assertions.assertEquals(expectedTotalAmount, actualTotalAmount);
    }
}
