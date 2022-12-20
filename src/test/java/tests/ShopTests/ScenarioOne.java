package tests.ShopTests;

import com.endava.models.Item;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ScenarioOne extends BaseTest {
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
        loginPage.openPage();
        loginPage.userLogin(LoginPage.USERNAME, LoginPage.PASSWORD);
    }

    @Test
    public void verifySuccessfulOrder() {
        List<Item> expectedProducts = new ArrayList<>();
        List<Item> unsortedItemList = homePage.getItemsList();
        unsortedItemList.sort(Comparator.comparing(Item::getPrice));
        Random random = new Random();
        Item i1 = unsortedItemList.get(random.nextInt(unsortedItemList.size()));
        Double firstItem = i1.getPrice();
        Item i2 = unsortedItemList.get(random.nextInt(unsortedItemList.size()));
        Double secondItem = i2.getPrice();

        expectedProducts.addAll(homePage.chooseItemByValue(firstItem));
        expectedProducts.addAll(homePage.chooseItemByValue(secondItem));
        homePage.viewCart();
        int actualCount = cartPage.getCartCount();
        Assert.assertEquals(actualCount,"2");

    }
}
