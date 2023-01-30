package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageHeader {
    private WebDriver driver;

    private static final By CART_ITEM_COUNT = By.xpath("//span[contains(@class, 'shopping_cart_badge')]");
    private static final By BURGER_MENU = By.id("react-burger-menu-btn");

    public PageHeader(WebDriver driver) {
        this.driver = driver;
    }
    public int getCartCount() {
        return Integer.parseInt(driver.findElement(CART_ITEM_COUNT).getText());
    }
    public boolean isCartItemsIconPresent() {
        return driver.findElements(CART_ITEM_COUNT).size() > 0;
    }
}
