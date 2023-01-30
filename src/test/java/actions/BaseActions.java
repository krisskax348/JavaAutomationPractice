package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseActions {
    private WebDriver driver;

    public BaseActions(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnButton(By elementLocator){
        driver.findElement(elementLocator).click();

    }
    public void typeInto(By elementLocator, String text){
        driver.findElement(elementLocator).sendKeys(text);
    }

}
