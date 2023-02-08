package tests.ShopTests;

import actions.LoggedUserActions;
import actions.UnauthenticatedUserActions;
import com.endava.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import tests.BaseTest;
import java.util.*;

public class TestSuccessfulOrder extends BaseTest {

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
    public void verifySuccessfulOrder() {
        List<Item> expectedProducts = new ArrayList<>();
        List<Item> unsortedItemList = userActions.getItems(HomePage.INVENTORY_ITEM, HomePage.INVENTORY_ITEM_NAME, HomePage.INVENTORY_ITEM_PRICE,HomePage.INVENTORY_ITEM_DESCRIPTION);
        unsortedItemList.sort(Comparator.comparing(Item::getPrice));
        Random random = new Random();
        Item i1 = unsortedItemList.get(random.nextInt(unsortedItemList.size()));
        Double lowestPrice = i1.getPrice();

        expectedProducts.addAll(userActions.chooseItemByValue(lowestPrice));

        userActions.clickOnButton(HomePage.SHOPPING_CART);
        List<Item> actualProducts = userActions.getItems(CartPage.CART_ITEM, CartPage.CART_ITEM_NAME, CartPage.CART_ITEM_PRICE, CartPage.CART_ITEM_DESCRIPTION);
        Assertions.assertEquals(expectedProducts, actualProducts);
        userActions.clickOnButton(CartPage.CHECKOUT_BUTTON);

        userActions.fillOutOrderDetails();

        userActions.clickOnButton(FinalizingOrderPage.FINISH_ORDER_BUTTON);

        String actual = userActions.getMessage(CompleteOrderPage.CONFIRMATION_HEADER);
        Assertions.assertEquals("THANK YOU FOR YOUR ORDER", actual);
    }
}
