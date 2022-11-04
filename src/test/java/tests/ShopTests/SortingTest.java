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
        loginPage.userLogin("standard_user", "secret_sauce");

        List<Item> unsortedItemList = homePage.getItemsList();
        unsortedItemList.sort(Comparator.comparing(Item::getPrice));
        homePage.applyFilter("lohi");
        List<Item> webFilteredItemList = homePage.getItemsList();

        Assertions.assertEquals(unsortedItemList,webFilteredItemList);

    }
    @Test
    public void verifySortingByDescendingPrice(){
        loginPage.openPage();
        loginPage.userLogin("standard_user", "secret_sauce");

        List<Item> unsortedItemList = homePage.getItemsList();
        unsortedItemList.sort(Comparator.comparing(Item::getPrice).reversed());
        homePage.applyFilter("hilo");
        List<Item> webFilteredItemList = homePage.getItemsList();

        Assertions.assertEquals(unsortedItemList,webFilteredItemList);
    }

    @Test
    public void verifySortingByAscendingName(){
        loginPage.openPage();
        loginPage.userLogin("standard_user", "secret_sauce");

        List<Item> unsortedItemList = homePage.getItemsList();
        unsortedItemList.sort(Comparator.comparing(Item::getName));
        homePage.applyFilter("az");
        List<Item> webFilteredItemList = homePage.getItemsList();

        Assertions.assertEquals(unsortedItemList,webFilteredItemList);
    }

    @Test
    public void verifySortingByDescendingName(){
        loginPage.openPage();
        loginPage.userLogin("standard_user", "secret_sauce");

        List<Item> unsortedItemList = homePage.getItemsList();
        unsortedItemList.sort(Comparator.comparing(Item::getName).reversed());
        homePage.applyFilter("za");
        List<Item> webFilteredItemList = homePage.getItemsList();

        Assertions.assertEquals(unsortedItemList,webFilteredItemList);
    }
}
