package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinalizingOrderPage {
    private WebDriver driver;
    private static final By FINISH_ORDER_BUTTON = By.name("finish");
    private static final By CANCEL_ORDER_BUTTON = By.id("cancel");
    private static final By PAYMENT_INFO = By.xpath("//div[.='SauceCard #31337']");
    private static final By SHIPPING_INFO = By.xpath("//div[.='FREE PONY EXPRESS DELIVERY!']");
    private static final By SUBTOTAL_AMOUNT = By.cssSelector(".summary_subtotal_label");
    private static final By TAX_AMOUNT = By.cssSelector(".summary_tax_label");
    private static final By FINAL_AMOUNT = By.cssSelector(".summary_total_label");


    public FinalizingOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void finishOrder() {
        driver.findElement(FINISH_ORDER_BUTTON).click();
    }

    public String getPaymentInfo() {
        return driver.findElement(PAYMENT_INFO).getText();
    }

    public String getShippingInfo() {
        return driver.findElement(SHIPPING_INFO).getText();
    }

    public double getSubtotalAmount() {
        return Double.parseDouble(driver.findElement(SUBTOTAL_AMOUNT).getText().replace("Item total: $", ""));
    }

    public double getTaxAmount() {
        return Double.parseDouble(driver.findElement(TAX_AMOUNT).getText().replace("Tax: $", ""));
    }

    public double getFinalAmount() {
        return Double.parseDouble(driver.findElement(FINAL_AMOUNT).getText().replace("Total: $", ""));
    }
}