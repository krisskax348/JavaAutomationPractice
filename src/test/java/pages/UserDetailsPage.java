package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDetailsPage {
    private WebDriver driver;
    private final By FIRST_NAME = By.id("first-name");
    private final By LAST_NAME = By.id("last-name");
    private final By POSTAL_CODE = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By CANCEL_CHECKOUT_BUTTON = By.id("cancel");

    public UserDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserDetails(String fName, String lName, String pCode) {
        driver.findElement(FIRST_NAME).sendKeys(fName);
        driver.findElement(LAST_NAME).sendKeys(lName);
        driver.findElement(POSTAL_CODE).sendKeys(pCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }
}