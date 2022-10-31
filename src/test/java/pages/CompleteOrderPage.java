package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompleteOrderPage {
    private WebDriver driver;
    private By confirmationHeader = By.xpath("//h2[@class=\"complete-header\"]");

    public CompleteOrderPage(WebDriver driver){
        this.driver = driver;
    }
    public String getConfirmationText(){
        return driver.findElement(confirmationHeader).getText();
    }
}
