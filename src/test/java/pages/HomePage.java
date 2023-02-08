package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public static final By SHOPPING_CART = By.className("shopping_cart_link");
    public static final By BACKPACK_ADD_BUTTON = By.id("add-to-cart-sauce-labs-backpack");
    public static final By BIKE_LIGHT_CART_ADD_BUTTON = By.id("add-to-cart-sauce-labs-bike-light");
    public static final By INVENTORY_ITEM = By.cssSelector(".inventory_item");
    public static final By INVENTORY_ITEM_PRICE = By.cssSelector(".inventory_item_price");
    public static final By INVENTORY_ITEM_NAME = By.cssSelector(".inventory_item_name");
    public static final By INVENTORY_ITEM_DESCRIPTION = By.cssSelector(".inventory_item_desc");
    public static final By ADD_TO_CART_BUTTON = By.cssSelector(".pricebar > button");
    public static final By FILTER_MENU = By.className("product_sort_container");
    public static final String ASCENDING_PRICE = "lohi";
    public static final String DESCENDING_PRICE = "hilo";
    public static final String ALPHABETIC_ORDER = "az";
    public static final String REVERSED_ALPHABETIC_ORDER = "za";


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

}
