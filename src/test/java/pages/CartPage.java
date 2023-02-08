package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    public static final By CHECKOUT_BUTTON = By.id("checkout");
    public static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    public static final By REMOVE_ITEM_BUTTON = By.xpath("//button[text()='Remove']");
    public static final By CART_ITEM_PRICE = By.cssSelector(".inventory_item_price");
    public static final By CART_ITEM = By.cssSelector(".cart_item");
    public static final By CART_ITEM_NAME = By.cssSelector(".inventory_item_name");
    public static final By CART_ITEM_DESCRIPTION = By.cssSelector(".inventory_item_desc");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
}

