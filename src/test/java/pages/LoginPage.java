package pages;

import com.endava.utils.PropertiesManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage {
  private WebDriver driver;
  public static final String BASE_URL = PropertiesManager.getProperties("baseUrl");
  private By usernameField = By.id("user-name");
  private By passField = By.id("password");
  private By loginButton = By.id("login-button");
  private By productsTitle = By.className("title");
  private By errorMessageLogin = By.xpath("//h3[@data-test=\"error\"]");

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }
  public void openPage() {
    driver.get(BASE_URL);
  }
  public void userLogin(String username, String password) {
    driver.findElement(usernameField).sendKeys(username);
    driver.findElement(passField).sendKeys(password);
    driver.findElement(loginButton).click();
  }
  public String getProductsTitle() {
    return driver.findElement(productsTitle).getText();
  }
  public String getWrongCredentialsMessage() {
    return driver.findElement(errorMessageLogin).getText();
  }
}