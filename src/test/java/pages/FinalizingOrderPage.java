package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinalizingOrderPage {
    private WebDriver driver;
    private static final By FINISH_ORDER_BUTTON = By.name("finish");
    private static final By CANCEL_ORDER_BUTTON = By.id("cancel");
    public FinalizingOrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void finishOrder(){
        driver.findElement(FINISH_ORDER_BUTTON).click();
    }
}
