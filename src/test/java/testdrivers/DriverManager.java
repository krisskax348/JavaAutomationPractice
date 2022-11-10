package testdrivers;

import com.endava.utils.ResourcesManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class DriverManager {

    public WebDriver createChromeDriver() {
        File chDr = ResourcesManager.getResourceAsFile("chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chDr.getPath());

        return new ChromeDriver();
    }

    public WebDriver createFirefoxDriver() {
        File ffDr = ResourcesManager.getResourceAsFile("geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", ffDr.getPath());

        return new FirefoxDriver();
    }

    public WebDriver createEdgeDriver() {
        File edgeDr = ResourcesManager.getResourceAsFile("msedgedriver.exe");
        System.setProperty("webdriver.edge.driver", edgeDr.getPath());

        return new EdgeDriver();
    }

}
