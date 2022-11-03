package tests.ShopTests;

import com.endava.models.Item;
import com.endava.utils.ItemNameComparator;
import com.endava.utils.ItemPriceComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import tests.BaseTest;
import java.util.Collections;
import java.util.List;

public class SortingTest extends BaseTest {

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
    public void verifySortingByAscendingPrice(){
        loginPage.openPage();
        loginPage.userLogin("standard_user", "secret_sauce");

        List<Item> unsortedItemList = homePage.getItemsList();
        Collections.sort(unsortedItemList, new ItemPriceComparator());
        homePage.applyFilter("lohi");
        List<Item> webFilteredItemList = homePage.getItemsList();

        Assertions.assertEquals(unsortedItemList,webFilteredItemList);

    }
    @Test
    public void verifySortingByDescendingPrice(){
        loginPage.openPage();
        loginPage.userLogin("standard_user", "secret_sauce");

        List<Item> unsortedItemList = homePage.getItemsList();
        Collections.sort(unsortedItemList, new ItemPriceComparator());
        homePage.applyFilter("hilo");
        List<Item> webFilteredItemList = homePage.getItemsList();

        Assertions.assertEquals(unsortedItemList,webFilteredItemList);
    }

    @Test
    public void verifySortingByAscendingName(){
        loginPage.openPage();
        loginPage.userLogin("standard_user", "secret_sauce");

        List<Item> unsortedItemList = homePage.getItemsList();
        Collections.sort(unsortedItemList,new ItemNameComparator());
        homePage.applyFilter("az");
        List<Item> webFilteredItemList = homePage.getItemsList();

        Assertions.assertEquals(unsortedItemList,webFilteredItemList);
    }

    @Test
    public void verifySortingByDescendingName(){
        loginPage.openPage();
        loginPage.userLogin("standard_user", "secret_sauce");

        List<Item> unsortedItemList = homePage.getItemsList();
        Collections.sort(unsortedItemList,new ItemNameComparator());
        homePage.applyFilter("za");
        List<Item> webFilteredItemList = homePage.getItemsList();

        Assertions.assertEquals(unsortedItemList,webFilteredItemList);
    }

}
