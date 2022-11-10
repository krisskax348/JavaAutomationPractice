package tests;

import testdrivers.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    protected WebDriver driver;

    public void driverSetup() {
        this.driver = new DriverFactory().createInstance();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

