package testdrivers;

import com.endava.utils.ResourcesManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class DriverManager {


    public WebDriver createChromeDriver() {
        File driverFile = ResourcesManager.getResourceAsFile("chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", driverFile.getPath());

        return new ChromeDriver();
    }

    public WebDriver createFirefoxDriver() {
        File driverFile = ResourcesManager.getResourceAsFile("geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", driverFile.getPath());

        return new FirefoxDriver();
    }

    public WebDriver createEdgeDriver() {
        File driverFile = ResourcesManager.getResourceAsFile("msedgedriver.exe");
        System.setProperty("webdriver.edge.driver", driverFile.getPath());

        return new EdgeDriver();
    }

}
