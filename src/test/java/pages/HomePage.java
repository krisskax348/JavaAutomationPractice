package pages;

import models.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private WebDriver driver;

    //private By filterMenu =By.className("product_sort_container");
    //private By filter = By.className("product_sort_container");
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
   public void applyFilter(){
       Select filterMenu = new Select(driver.findElement(By.className("product_sort_container")));
       filterMenu.selectByValue("hilo");
    }
    public List<Item> chooseItemByValue(String value) {
       List<Item> items = new ArrayList<>();
        List<WebElement> elements = driver.findElements(inventoryItem);

        for (WebElement element : elements) {
            WebElement price = element.findElement(inventoryItemPrice);
            if (price.getText().contains(value)) {

                element.findElement(addToCartButton).click();
                String productPrice = element.findElement(inventoryItemPrice).getText();
                String productName = element.findElement(inventoryItemName).getText();
                String productDesc = element.findElement(inventoryItemDescription).getText();
                Item item = new Item(productName,productDesc,productPrice);
                items.add(item);

            }
        }
        return items;
    }
}

