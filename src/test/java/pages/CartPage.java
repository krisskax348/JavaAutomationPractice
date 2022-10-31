package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public void proceedToCheckout(){

        driver.findElement(checkoutButton).click();
    }
    public void getPricesOfItemsInCart(){
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class=\"item_pricebar\"]"));
    }
}
