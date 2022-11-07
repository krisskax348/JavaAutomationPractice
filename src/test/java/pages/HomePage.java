package pages;

import com.endava.models.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private WebDriver driver;

    private static final By SHOPPING_CART = By.className("shopping_cart_link");
    private static final By BACKPACK_ADD_BUTTON = By.id("add-to-cart-sauce-labs-backpack");
    private static final By BIKE_LIGHT_CART_ADD_BUTTON = By.id("add-to-cart-sauce-labs-bike-light");
    private static final By INVENTORY_ITEM = By.cssSelector(".inventory_item");
    private static final By INVENTORY_ITEM_PRICE = By.cssSelector(".inventory_item_price");
    private static final By INVENTORY_ITEM_NAME = By.cssSelector(".inventory_item_name");
    private static final By INVENTORY_ITEM_DESCRIPTION = By.cssSelector(".inventory_item_desc");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector(".pricebar > button");
    private static final By FILTER_MENU = By.className("product_sort_container");
   public HomePage(WebDriver driver){
       this.driver = driver;
   }
   public void selectItems(){
       driver.findElement(BACKPACK_ADD_BUTTON).click();
       driver.findElement(BIKE_LIGHT_CART_ADD_BUTTON).click();
   }
   public void viewCart(){
       driver.findElement(SHOPPING_CART).click();
   }
   public void applyFilter(String value) {
       Select select = new Select(driver.findElement(FILTER_MENU));
       select.selectByValue(value);
   }
    public List<Item> chooseItemByValue(Double value) {
       List<Item> items = new ArrayList<>();
        List<WebElement> elements = driver.findElements(INVENTORY_ITEM);

        for (WebElement element : elements) {
            WebElement price = element.findElement(INVENTORY_ITEM_PRICE);
            if (price.getText().contains(value.toString())) {

                element.findElement(ADD_TO_CART_BUTTON).click();
                double productPrice = Double.parseDouble(element.findElement(INVENTORY_ITEM_PRICE).getText().replace("$", ""));
                String productName = element.findElement(INVENTORY_ITEM_NAME).getText();
                String productDesc = element.findElement(INVENTORY_ITEM_DESCRIPTION).getText();
                Item item = new Item(productName,productDesc,productPrice);
                items.add(item);
                break;

            }
        }
        return items;
    }
    public List<Item> getItemsList(){
        List<Item> items = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.cssSelector(".inventory_item"));
        for(WebElement element : elements){
            String name = element.findElement(INVENTORY_ITEM_NAME).getText();
            double price = Double.parseDouble(element.findElement(INVENTORY_ITEM_PRICE).getText().replace("$", ""));
            String desc = element.findElement(INVENTORY_ITEM_DESCRIPTION).getText();
            Item item = new Item(name,desc,price);
            items.add(item);
        }
        return items;
    }
}

