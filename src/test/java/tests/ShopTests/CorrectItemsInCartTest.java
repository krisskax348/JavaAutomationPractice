package tests.ShopTests;

import com.endava.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.v85.page.Page;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PageHeader;
import tests.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class CorrectItemsInCartTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private PageHeader pageHeader;


    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        pageHeader = new PageHeader(driver);
        loginPage.openPage();
        loginPage.userLogin(LoginPage.USERNAME, LoginPage.PASSWORD);
    }

    @Test
    public void verifyCorrectItemsInCart() {
        List<Item> expectedItems = new ArrayList<>();

        Item item1 = homePage.addRandomItemToCart();
        Item item2 = homePage.addRandomItemToCart();

        homePage.viewCart();
        cartPage.removeCartItem(1);
        int actualCount = pageHeader.getCartCount();
        Assertions.assertEquals(1, actualCount);

        expectedItems.add(item1);
        List<Item> actualCartItems = cartPage.getItemsInCart();
        Assertions.assertEquals(expectedItems, actualCartItems);

    }
}
