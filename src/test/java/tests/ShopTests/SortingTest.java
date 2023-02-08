package tests.ShopTests;

import actions.LoggedUserActions;
import actions.UnauthenticatedUserActions;
import com.endava.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import tests.BaseTest;
import java.util.Comparator;
import java.util.List;

import static pages.HomePage.*;

public class SortingTest extends BaseTest {

    private LoggedUserActions userActions;
    private UnauthenticatedUserActions unauthenticatedUserActions;

    @BeforeEach
    public void setup() {
        driverSetup();
        unauthenticatedUserActions = new UnauthenticatedUserActions(driver);
        userActions = new LoggedUserActions(driver);

        unauthenticatedUserActions.openPage(LoginPage.BASE_URL);
        unauthenticatedUserActions.login(LoginPage.USERNAME, LoginPage.PASSWORD);

    }

    @Test
    public void verifySortingByAscendingPrice() {
        List<Item> expectedList = userActions.getItems(INVENTORY_ITEM, INVENTORY_ITEM_NAME, INVENTORY_ITEM_PRICE, INVENTORY_ITEM_DESCRIPTION);
        expectedList.sort(Comparator.comparing(Item::getPrice));
        userActions.applyFilter(ASCENDING_PRICE);

        List<Item> actualList = userActions.getItems(INVENTORY_ITEM, INVENTORY_ITEM_NAME, INVENTORY_ITEM_PRICE, INVENTORY_ITEM_DESCRIPTION);

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void verifySortingByDescendingPrice() {
        List<Item> expectedList = userActions.getItems(INVENTORY_ITEM, INVENTORY_ITEM_NAME, INVENTORY_ITEM_PRICE, INVENTORY_ITEM_DESCRIPTION);
        expectedList.sort(Comparator.comparing(Item::getPrice).reversed());
        userActions.applyFilter(DESCENDING_PRICE);
        List<Item> actualList = userActions.getItems(INVENTORY_ITEM, INVENTORY_ITEM_NAME, INVENTORY_ITEM_PRICE, INVENTORY_ITEM_DESCRIPTION);

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void verifySortingByAscendingName() {
        List<Item> expectedList = userActions.getItems(INVENTORY_ITEM, INVENTORY_ITEM_NAME, INVENTORY_ITEM_PRICE, INVENTORY_ITEM_DESCRIPTION);
        expectedList.sort(Comparator.comparing(Item::getName));
        userActions.applyFilter(ALPHABETIC_ORDER);
        List<Item> actualList = userActions.getItems(INVENTORY_ITEM, INVENTORY_ITEM_NAME, INVENTORY_ITEM_PRICE, INVENTORY_ITEM_DESCRIPTION);

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void verifySortingByDescendingName() {
        List<Item> expectedList = userActions.getItems(INVENTORY_ITEM, INVENTORY_ITEM_NAME, INVENTORY_ITEM_PRICE, INVENTORY_ITEM_DESCRIPTION);
        expectedList.sort(Comparator.comparing(Item::getName).reversed());
        userActions.applyFilter(REVERSED_ALPHABETIC_ORDER);
        List<Item> actualList = userActions.getItems(INVENTORY_ITEM, INVENTORY_ITEM_NAME, INVENTORY_ITEM_PRICE, INVENTORY_ITEM_DESCRIPTION);

        Assertions.assertEquals(expectedList, actualList);
    }
}
