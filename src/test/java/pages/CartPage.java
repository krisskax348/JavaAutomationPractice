package pages;

import com.endava.models.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");
    private By inventoryItemPrice = By.cssSelector(".inventory_item_price");
    private By inventoryItemName = By.cssSelector(".inventory_item_name");
    private By inventoryItemDescription = By.cssSelector(".inventory_item_desc");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public void proceedToCheckout(){

        driver.findElement(checkoutButton).click();
    }
    public List<Item> getItemsInCart(){
        List<Item> items = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.cssSelector(".cart_item"));
        for(WebElement element : elements){
            String name = element.findElement(inventoryItemName).getText();
            double price = Double.parseDouble(element.findElement(inventoryItemPrice).getText().replace("$", ""));
            String desc = element.findElement(inventoryItemDescription).getText();
            Item item = new Item(name,desc,price);
            items.add(item);
        }
        return items;
    }
}
