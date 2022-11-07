package pages;

import com.endava.utils.PropertiesManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage {
  private WebDriver driver;
  public static final String BASE_URL = PropertiesManager.getProperties("baseUrl");
  private static final By USERNAME_FIELD = By.id("user-name");
  private static final By PASS_FIELD = By.id("password");
  public static final String USERNAME = "standard_user";
  public static final String PASSWORD = "secret_sauce";
  private static final By LOGIN_BUTTON = By.id("login-button");
  private static final By PRODUCTS_TITLE = By.className("title");
  private static final By ERROR_MESSAGE_LOGIN = By.xpath("//h3[@data-test=\"error\"]");

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }
  public void openPage() {
    driver.get(BASE_URL);
  }
  public void userLogin(String username, String password) {
    driver.findElement(USERNAME_FIELD).sendKeys(username);
    driver.findElement(PASS_FIELD).sendKeys(password);
    driver.findElement(LOGIN_BUTTON).click();
  }
  public String getProductsTitle() {
    return driver.findElement(PRODUCTS_TITLE).getText();
  }
  public String getWrongCredentialsMessage() {
    return driver.findElement(ERROR_MESSAGE_LOGIN).getText();
  }
}