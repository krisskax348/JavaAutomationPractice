package tests.ShopTests;

import com.endava.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import tests.BaseTest;

import java.util.Comparator;
import java.util.List;

import static pages.HomePage.*;

public class SortingTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        loginPage.openPage();
        loginPage.userLogin(LoginPage.USERNAME, LoginPage.PASSWORD);
    }

    @Test
    public void verifySortingByAscendingPrice() {
        List<Item> expectedlist = homePage.getItemsList();
        expectedlist.sort(Comparator.comparing(Item::getPrice));
        homePage.applyFilter(ASCENDING_PRICE);
        List<Item> actualList = homePage.getItemsList();

        Assertions.assertEquals(expectedlist, actualList);
    }

    @Test
    public void verifySortingByDescendingPrice() {
        List<Item> expectedList = homePage.getItemsList();
        expectedList.sort(Comparator.comparing(Item::getPrice).reversed());
        homePage.applyFilter(DESCENDING_PRICE);
        List<Item> actualList = homePage.getItemsList();

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void verifySortingByAscendingName() {
        List<Item> expectedList = homePage.getItemsList();
        expectedList.sort(Comparator.comparing(Item::getName));
        homePage.applyFilter(ALPHABETIC_ORDER);
        List<Item> actualList = homePage.getItemsList();

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void verifySortingByDescendingName() {
        List<Item> expectedList = homePage.getItemsList();
        expectedList.sort(Comparator.comparing(Item::getName).reversed());
        homePage.applyFilter(REVERSED_ALPHABETIC_ORDER);
        List<Item> actualList = homePage.getItemsList();

        Assertions.assertEquals(expectedList, actualList);
    }
}
