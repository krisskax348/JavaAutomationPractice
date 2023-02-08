package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

abstract class BaseActions {
    protected WebDriver driver;

    public BaseActions(WebDriver driver) {
       this.driver = driver;
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void clickOnButton(By elementLocator) {
        driver.findElement(elementLocator).click();

    }

    public void enterText(By elementLocator, String text) {
        driver.findElement(elementLocator).sendKeys(text);
    }

    public String getMessage(By elementLocator) {
        return driver.findElement(elementLocator).getText();
    }

}
