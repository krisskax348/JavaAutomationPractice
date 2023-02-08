package tests.ShopTests;

import actions.LoggedUserActions;
import actions.UnauthenticatedUserActions;
import com.endava.models.Item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class VerifyShipmentInfoTests extends BaseTest {
    private UnauthenticatedUserActions unauthenticatedUserActions;
    private LoggedUserActions userActions;


    @BeforeEach
    public void setup() {
        driverSetup();
        unauthenticatedUserActions = new UnauthenticatedUserActions(driver);
        userActions = new LoggedUserActions(driver);

        unauthenticatedUserActions.openPage(LoginPage.BASE_URL);
        unauthenticatedUserActions.login(LoginPage.USERNAME, LoginPage.PASSWORD);
    }

    @Test
    public void emptyOrderTest() {
        List<Item> expectedItems = new ArrayList<>();
        userActions.clickOnButton(HomePage.SHOPPING_CART);
        List<Item> actualCartItems = userActions.getItems(CartPage.CART_ITEM, CartPage.CART_ITEM_NAME, CartPage.CART_ITEM_PRICE, CartPage.CART_ITEM_DESCRIPTION);
        Assertions.assertEquals(expectedItems, actualCartItems);

        userActions.clickOnButton(CartPage.CHECKOUT_BUTTON);
        userActions.fillOutOrderDetails();

        String actualPaymentInfo = userActions.getMessage(FinalizingOrderPage.PAYMENT_INFO);
        String actualShippingInfo = userActions.getMessage(FinalizingOrderPage.SHIPPING_INFO);
        double actualSubtotalAmount = userActions.getSubtotalAmount();
        double actualTaxAmount = userActions.getTaxAmount();
        double actualTotalAmount = userActions.getFinalAmount();


        Assertions.assertEquals("SauceCard #31337", actualPaymentInfo);
        Assertions.assertEquals("FREE PONY EXPRESS DELIVERY!", actualShippingInfo);
        Assertions.assertEquals(0, actualSubtotalAmount);
        Assertions.assertEquals(0.00, actualTaxAmount);
        Assertions.assertEquals(0.00, actualTotalAmount);


    }

    @Test
    public void oneItemOrderTest() {
        List<Item> expectedItems = new ArrayList<>();
        Item item1 = userActions.addRandomItemToCart();
        expectedItems.add(item1);

        double expectedSubtotalAmount = item1.getPrice();
        double expectedTax = Math.round((8 * expectedSubtotalAmount / 100) * 100.0) / 100.0;
        double expectedTotalAmount = expectedSubtotalAmount + expectedTax;

        userActions.clickOnButton(HomePage.SHOPPING_CART);
        List<Item> actualCartItems = userActions.getItems(CartPage.CART_ITEM, CartPage.CART_ITEM_NAME, CartPage.CART_ITEM_PRICE, CartPage.CART_ITEM_DESCRIPTION);
        Assertions.assertEquals(expectedItems, actualCartItems);

        userActions.clickOnButton(CartPage.CHECKOUT_BUTTON);
        userActions.fillOutOrderDetails();

        String actualPaymentInfo = userActions.getMessage(FinalizingOrderPage.PAYMENT_INFO);
        String actualShippingInfo = userActions.getMessage(FinalizingOrderPage.SHIPPING_INFO);
        double actualSubtotalAmount = userActions.getSubtotalAmount();
        double actualTaxAmount = userActions.getTaxAmount();
        double actualTotalAmount = userActions.getFinalAmount();

        Assertions.assertEquals("SauceCard #31337", actualPaymentInfo);
        Assertions.assertEquals("FREE PONY EXPRESS DELIVERY!", actualShippingInfo);
        Assertions.assertEquals(expectedSubtotalAmount, actualSubtotalAmount);
        Assertions.assertEquals(expectedTax, actualTaxAmount);
        Assertions.assertEquals(expectedTotalAmount, actualTotalAmount);

    }

    @Test
    public void twoItemsOrderTest() {
        List<Item> expectedItems = new ArrayList<>();
        Item item1 = userActions.addRandomItemToCart();
        Item item2 = userActions.addRandomItemToCart();
        expectedItems.add(item1);
        expectedItems.add(item2);

        double expectedSubtotalAmount = item1.getPrice() + item2.getPrice();
        double expectedTax = Math.round((8 * expectedSubtotalAmount / 100) * 100.0) / 100.0;
        double expectedTotalAmount = expectedSubtotalAmount + expectedTax;

        userActions.clickOnButton(HomePage.SHOPPING_CART);
        List<Item> actualCartItems = userActions.getItems(CartPage.CART_ITEM, CartPage.CART_ITEM_NAME, CartPage.CART_ITEM_PRICE, CartPage.CART_ITEM_DESCRIPTION);
        Assertions.assertEquals(expectedItems, actualCartItems);

        userActions.clickOnButton(CartPage.CHECKOUT_BUTTON);
        userActions.fillOutOrderDetails();


        String actualPaymentInfo = userActions.getMessage(FinalizingOrderPage.PAYMENT_INFO);
        String actualShippingInfo = userActions.getMessage(FinalizingOrderPage.SHIPPING_INFO);
        double actualSubtotalAmount = userActions.getSubtotalAmount();
        double actualTaxAmount = userActions.getTaxAmount();
        double actualTotalAmount = userActions.getFinalAmount();

        Assertions.assertEquals("SauceCard #31337", actualPaymentInfo);
        Assertions.assertEquals("FREE PONY EXPRESS DELIVERY!", actualShippingInfo);
        Assertions.assertEquals(expectedSubtotalAmount, actualSubtotalAmount);
        Assertions.assertEquals(expectedTax, actualTaxAmount);
        Assertions.assertEquals(expectedTotalAmount, actualTotalAmount);
    }
}
