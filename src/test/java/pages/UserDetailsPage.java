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
    private final By ERROR_MESSAGE_USER_DETAILS = By.xpath("//h3[@data-test=\"error\"]");

    public UserDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserDetails(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public String getWrongUserDetailsMessage() {
        return driver.findElement(ERROR_MESSAGE_USER_DETAILS).getText();
    }
}