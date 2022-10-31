package testdrivers;
import com.endava.exceptions.BrowserNotSupportedException;
import com.endava.utils.PropertiesManager;
import org.openqa.selenium.WebDriver;
public class DriverFactory {
    public WebDriver createInstance() {
        WebDriver driver;

        String browserName = PropertiesManager.getProperties("browser");
        BrowserList browserType = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserType) {

            case CHROME:
                driver = new DriverManager().createChromeDriver();
                driver.manage().window().maximize();
                break;
            case FIREFOX:
                driver = new DriverManager().createFirefoxDriver();
                driver.manage().window().maximize();
                break;
            case EDGE:
                driver = new DriverManager().createEdgeDriver();
                driver.manage().window().maximize();
                break;
            default:
                throw new BrowserNotSupportedException(browserName);
        }
        return driver;
    }
    public enum BrowserList {
        CHROME, FIREFOX, EDGE
    }
}
