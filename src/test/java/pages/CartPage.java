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

    private static final By REMOVE_ITEM_BUTTON = By.xpath("//button[text()='Remove']");
    private static final By INVENTORY_ITEM_PRICE = By.cssSelector(".inventory_item_price");
    private static final By CART_ITEM = By.cssSelector(".cart_item");
    private static final By INVENTORY_ITEM_NAME = By.cssSelector(".inventory_item_name");
    private static final By INVENTORY_ITEM_DESCRIPTION = By.cssSelector(".inventory_item_desc");

    private static final By CART_ITEM_COUNT = By.xpath("//span[contains(@class, 'shopping_cart_badge')]");


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() {

        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public List<Item> getItemsInCart() {
        List<Item> items = new ArrayList<>();
        List<WebElement> elements = driver.findElements(CART_ITEM);
        for (WebElement element : elements) {
            String name = element.findElement(INVENTORY_ITEM_NAME).getText();
            double price = Double.parseDouble(element.findElement(INVENTORY_ITEM_PRICE).getText().replace("$", ""));
            String desc = element.findElement(INVENTORY_ITEM_DESCRIPTION).getText();
            Item item = new Item(name, desc, price);
            items.add(item);

        }
        return items;
    }

    public int getCartCount() {
        return Integer.parseInt(driver.findElement(CART_ITEM_COUNT).getText());
    }

    public List<Item> removeItemFromList() {
        List<WebElement> elements = driver.findElements(CART_ITEM);
        List<Item> items = new ArrayList<>();
        for (WebElement element : elements) {
            if (element.equals(elements.get(0))) {
                element.findElement(REMOVE_ITEM_BUTTON).click();
            } else {
                String name = element.findElement(INVENTORY_ITEM_NAME).getText();
                double price = Double.parseDouble(element.findElement(INVENTORY_ITEM_PRICE).getText().replace("$", ""));
                String desc = element.findElement(INVENTORY_ITEM_DESCRIPTION).getText();
                Item item = new Item(name, desc, price);
                items.add(item);
            }
        }
        return items;
    }

    public void deleteCartItem(int itemNumber) {
        List<WebElement> cartItems = driver.findElements(CART_ITEM);
        cartItems.get(itemNumber).findElement(REMOVE_ITEM_BUTTON).click();
    }

    public boolean isIconPresent() {
        return driver.findElements(CART_ITEM_COUNT).size() > 0;
    }

}

