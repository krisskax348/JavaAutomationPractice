package tests.ShopTests;

import actions.LoggedUserActions;
import actions.UnauthenticatedUserActions;
import com.endava.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PageHeader;
import tests.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class CorrectItemsInCartTest extends BaseTest {
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
    public void verifyCorrectItemsInCart() {
        List<Item> expectedItems = new ArrayList<>();

        Item item1 = userActions.addRandomItemToCart();
        Item item2 = userActions.addRandomItemToCart();

        userActions.clickOnButton(HomePage.SHOPPING_CART);
        userActions.removeCartItem(1, CartPage.REMOVE_ITEM_BUTTON);
        int actualCount = userActions.getCartCount(PageHeader.CART_ITEM_COUNT);
        Assertions.assertEquals(1, actualCount);

        expectedItems.add(item1);
        List<Item> actualCartItems = userActions.getItems(CartPage.CART_ITEM, CartPage.CART_ITEM_NAME, CartPage.CART_ITEM_PRICE, CartPage.CART_ITEM_DESCRIPTION);
        Assertions.assertEquals(expectedItems, actualCartItems);

    }
}
