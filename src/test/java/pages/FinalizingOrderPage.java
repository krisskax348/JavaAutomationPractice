package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinalizingOrderPage {
    private WebDriver driver;
    public static final By FINISH_ORDER_BUTTON = By.name("finish");
    public static final By CANCEL_ORDER_BUTTON = By.id("cancel");
    public static final By PAYMENT_INFO = By.xpath("//div[.='SauceCard #31337']");
    public static final By SHIPPING_INFO = By.xpath("//div[.='FREE PONY EXPRESS DELIVERY!']");
    public static final By SUBTOTAL_AMOUNT = By.cssSelector(".summary_subtotal_label");
    public static final By TAX_AMOUNT = By.cssSelector(".summary_tax_label");
    public static final By FINAL_AMOUNT = By.cssSelector(".summary_total_label");


    public FinalizingOrderPage(WebDriver driver) {
        this.driver = driver;
    }


}