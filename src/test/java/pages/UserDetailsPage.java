package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDetailsPage {
    private WebDriver driver;
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By cancelCheckoutButton = By.id("cancel");

    public UserDetailsPage(WebDriver driver){
        this.driver = driver;
    }
    public void enterUserDetails(String fName, String lName, String pCode){
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(postalCode).sendKeys(pCode);
        driver.findElement(continueButton).click();
    }
}