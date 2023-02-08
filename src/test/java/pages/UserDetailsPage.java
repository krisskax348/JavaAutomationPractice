package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDetailsPage {
    private WebDriver driver;

    public static final By FIRST_NAME = By.id("first-name");
    public static final By LAST_NAME = By.id("last-name");
    public static final By POSTAL_CODE = By.id("postal-code");
    public static final By CONTINUE_BUTTON = By.id("continue");
    public static final By CANCEL_CHECKOUT_BUTTON = By.id("cancel");
    public static final By ERROR_MESSAGE_USER_DETAILS = By.xpath("//h3[@data-test=\"error\"]");

    public UserDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
}