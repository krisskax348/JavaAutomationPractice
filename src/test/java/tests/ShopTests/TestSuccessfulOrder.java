package tests.ShopTests;

import com.endava.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import tests.BaseTest;

import java.util.*;

public class TestSuccessfulOrder extends BaseTest {
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
        Double lowestPrice = i1.getPrice();

        expectedProducts.addAll(homePage.chooseItemByValue(lowestPrice));

        homePage.viewCart();
        List<Item> actualProducts = cartPage.getItemsInCart();
        Assertions.assertEquals(expectedProducts, actualProducts);

        cartPage.proceedToCheckout();

        userDetailsPage.enterUserDetails("Krisi", "Teneva", "6000");
        finalizingOrderPage.finishOrder();

        String actual = completeOrderPage.getConfirmationText();
        Assertions.assertEquals("THANK YOU FOR YOUR ORDER", actual);
    }
}
