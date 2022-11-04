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


    private By shoppingCart = By.className("shopping_cart_link");
    private By backpackAddButton = By.id("add-to-cart-sauce-labs-backpack");
    private By bikeLightCartAddButton = By.id("add-to-cart-sauce-labs-bike-light");
    private By inventoryItem = By.cssSelector(".inventory_item");
    private By inventoryItemPrice = By.cssSelector(".inventory_item_price");
    private By inventoryItemName = By.cssSelector(".inventory_item_name");
    private By inventoryItemDescription = By.cssSelector(".inventory_item_desc");
    private By addToCartButton = By.cssSelector(".pricebar > button");
   public HomePage(WebDriver driver){
       this.driver = driver;
   }
   public void selectItems(){
       driver.findElement(backpackAddButton).click();
       driver.findElement(bikeLightCartAddButton).click();
   }
   public void viewCart(){
       driver.findElement(shoppingCart).click();
   }
   public String applyFilter(String value){
       Select filterMenu = new Select(driver.findElement(By.className("product_sort_container")));
       if(value == "lohi"){
           filterMenu.selectByValue(value);
       }
       else if(value == "hilo"){
           filterMenu.selectByValue(value);
       }
       else if(value == "az"){
           filterMenu.selectByValue(value);
       }
       else if(value == "za"){
           filterMenu.selectByValue(value);
       }
       return value;
    }
    public List<Item> chooseItemByValue(Double value) {
       List<Item> items = new ArrayList<>();
        List<WebElement> elements = driver.findElements(inventoryItem);

        for (WebElement element : elements) {
            WebElement price = element.findElement(inventoryItemPrice);
            if (price.getText().contains(value.toString())) {

                element.findElement(addToCartButton).click();
                double productPrice = Double.parseDouble(element.findElement(inventoryItemPrice).getText().replace("$", ""));
                String productName = element.findElement(inventoryItemName).getText();
                String productDesc = element.findElement(inventoryItemDescription).getText();
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
            String name = element.findElement(inventoryItemName).getText();
            double price = Double.parseDouble(element.findElement(inventoryItemPrice).getText().replace("$", ""));
            String desc = element.findElement(inventoryItemDescription).getText();
            Item item = new Item(name,desc,price);
            items.add(item);
        }
        return items;
    }
}

