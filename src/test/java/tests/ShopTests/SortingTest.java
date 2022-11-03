package tests.ShopTests;

import com.endava.models.Item;
import com.endava.utils.ItemComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import tests.BaseTest;

import java.util.Collections;
import java.util.List;

public class SortingTest extends BaseTest {
    //sort items in list from homepage
    //apply filter in browser
    //
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
        Collections.sort(unsortedItemList, new ItemComparator());
        homePage.applyFilter("lohi");
        List<Item> webFilteredItemList = homePage.getItemsList();

        Assertions.assertEquals(unsortedItemList,webFilteredItemList);


    }

}
