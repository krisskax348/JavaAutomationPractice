package pages;

import com.endava.utils.PropertiesManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    public static final String BASE_URL = PropertiesManager.getProperties("baseUrl");
    public static final By USERNAME_FIELD = By.id("user-name");
    public static final By PASS_FIELD = By.id("password");
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By PRODUCTS_TITLE = By.className("title");
    public static final By ERROR_MESSAGE_LOGIN = By.xpath("//h3[@data-test=\"error\"]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
}