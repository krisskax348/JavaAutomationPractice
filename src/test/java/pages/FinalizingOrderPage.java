package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinalizingOrderPage {
    private WebDriver driver;
    private By finishOrderButton = By.name("finish");
    private By cancelOrderBtn = By.id("cancel");
    public FinalizingOrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void finishOrder(){
        driver.findElement(finishOrderButton).click();
    }
}
