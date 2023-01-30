package tests.ShopTests;

import com.endava.models.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.v85.page.Page;
import pages.*;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ItemsDeletionTest extends BaseTest {
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
    public void verifyItemsInCartAreDeleted() {
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(homePage.addRandomItemToCart());

        homePage.viewCart();
        List<Item> actualCartItems = cartPage.getItemsInCart();
        Assertions.assertEquals(expectedItems, actualCartItems);

        cartPage.removeCartItem(0);
        Assertions.assertFalse(pageHeader.isCartItemsIconPresent(), "Icon is present");
    }
}
