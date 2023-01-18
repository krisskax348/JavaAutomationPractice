package tests.ShopTests;

import com.endava.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import tests.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class CorrectItemsInCartTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;


    @BeforeEach
    public void setup() {
        driverSetup();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        loginPage.openPage();
        loginPage.userLogin(LoginPage.USERNAME, LoginPage.PASSWORD);
    }

    @Test
    public void verifyCorrectItemsInCart() {
        List<Item> expectedItems = new ArrayList<>();

        expectedItems.addAll(homePage.addRandomItemToCart());
        homePage.addRandomItemToCart();

        homePage.viewCart();
        int actualCount = cartPage.getCartCount();
        Assertions.assertEquals(2, actualCount);

        cartPage.deleteCartItem(1);
        List<Item> actualCartItems = cartPage.getItemsInCart();
        Assertions.assertEquals(expectedItems, actualCartItems);

    }
}
