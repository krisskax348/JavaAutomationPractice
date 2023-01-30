package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompleteOrderPage {
    private WebDriver driver;
    private static final By CONFIRMATION_HEADER = By.xpath("//h2[@class=\"complete-header\"]");

    public CompleteOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getConfirmationText() {
        return driver.findElement(CONFIRMATION_HEADER).getText();
    }
}
