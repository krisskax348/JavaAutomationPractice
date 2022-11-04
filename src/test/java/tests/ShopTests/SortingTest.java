package tests.ShopTests;

import com.endava.models.Item;
import constants.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import tests.BaseTest;
import java.util.Comparator;
import java.util.List;

public class SortingTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }
    @Test
    public void verifySortingByAscendingPrice(){
        loginPage.openPage();
        loginPage.userLogin(Constants.USERNAME, Constants.PASSWORD);

        List<Item> expectedlist = homePage.getItemsList();
        expectedlist.sort(Comparator.comparing(Item::getPrice));
        homePage.applyFilter(Constants.ASCENDING_PRICE);
        List<Item> actualList = homePage.getItemsList();

        Assertions.assertEquals(expectedlist,actualList);

    }
    @Test
    public void verifySortingByDescendingPrice(){
        loginPage.openPage();
        loginPage.userLogin(Constants.USERNAME, Constants.PASSWORD);

        List<Item> expectedList = homePage.getItemsList();
        expectedList.sort(Comparator.comparing(Item::getPrice).reversed());
        homePage.applyFilter(Constants.DESCENDING_PRICE);
        List<Item> actualList = homePage.getItemsList();

        Assertions.assertEquals(expectedList,actualList);
    }

    @Test
    public void verifySortingByAscendingName(){
        loginPage.openPage();
        loginPage.userLogin(Constants.USERNAME, Constants.PASSWORD);

        List<Item> expectedList = homePage.getItemsList();
        expectedList.sort(Comparator.comparing(Item::getName));
        homePage.applyFilter(Constants.ALPHABETIC_ORDER);
        List<Item> actualList = homePage.getItemsList();

        Assertions.assertEquals(expectedList,actualList);
    }

    @Test
    public void verifySortingByDescendingName(){
        loginPage.openPage();
        loginPage.userLogin(Constants.USERNAME, Constants.PASSWORD);

        List<Item> expectedList = homePage.getItemsList();
        expectedList.sort(Comparator.comparing(Item::getName).reversed());
        homePage.applyFilter(Constants.REVERSED_ALPHABETIC_ORDER);
        List<Item> actualList = homePage.getItemsList();

        Assertions.assertEquals(expectedList,actualList);
    }
}
