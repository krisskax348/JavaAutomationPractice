package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomePage {
    private WebDriver driver;

    //private By filterMenu =By.className("product_sort_container");
    //private By filter = By.className("product_sort_container");
    private By shoppingCart = By.className("shopping_cart_link");
    private By backpackAddButton = By.id("add-to-cart-sauce-labs-backpack");
    private By bikeLightCartAddButton = By.id("add-to-cart-sauce-labs-bike-light");
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
    public String chooseItemByValue(String value){
        List <WebElement> prices = driver.findElements(By.xpath("//div[@class=\"pricebar\"]"));
        for(WebElement price : prices){
            if(price.getText().contains(value)){
                By addToCart = RelativeLocator.with(By.tagName("button"))
                        .toRightOf(By.xpath("//div[contains(@class,\"inventory_item_price\") and text() = '" + value + "']"));
                driver.findElement(addToCart).click();
            }
        }
        return value;
    }
}

