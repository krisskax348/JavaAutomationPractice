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

public class ItemsDeletionTest extends BaseTest {

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
    public void verifyItemsInCartAreDeleted() {
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(userActions.addRandomItemToCart());

        userActions.clickOnButton(HomePage.SHOPPING_CART);
        List<Item> actualCartItems = userActions.getItems(CartPage.CART_ITEM,CartPage.CART_ITEM_NAME,CartPage.CART_ITEM_PRICE, CartPage.CART_ITEM_DESCRIPTION);
        Assertions.assertEquals(expectedItems, actualCartItems);

        userActions.removeCartItem(0,CartPage.REMOVE_ITEM_BUTTON);
        Assertions.assertFalse(userActions.isCartItemsIconPresent(PageHeader.CART_ITEM_COUNT), "Icon is present");
    }
}
