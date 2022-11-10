package pages;

import com.endava.models.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private static final By INVENTORY_ITEM_PRICE = By.cssSelector(".inventory_item_price");
    private static final By INVENTORY_ITEM_NAME = By.cssSelector(".inventory_item_name");
    private static final By INVENTORY_ITEM_DESCRIPTION = By.cssSelector(".inventory_item_desc");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() {

        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public List<Item> getItemsInCart() {
        List<Item> items = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.cssSelector(".cart_item"));
        for (WebElement element : elements) {
            String name = element.findElement(INVENTORY_ITEM_NAME).getText();
            double price = Double.parseDouble(element.findElement(INVENTORY_ITEM_PRICE).getText().replace("$", ""));
            String desc = element.findElement(INVENTORY_ITEM_DESCRIPTION).getText();
            Item item = new Item(name, desc, price);
            items.add(item);
        }
        return items;
    }
}
