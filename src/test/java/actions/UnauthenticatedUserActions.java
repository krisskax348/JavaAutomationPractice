package actions;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class UnauthenticatedUserActions extends BaseActions {

    public UnauthenticatedUserActions(WebDriver driver) {
        super(driver);
    }
    public void login(String username, String password){
        driver.findElement(LoginPage.USERNAME_FIELD).sendKeys(username);
        driver.findElement(LoginPage.PASS_FIELD).sendKeys(password);
        driver.findElement(LoginPage.LOGIN_BUTTON).click();
    }
}
