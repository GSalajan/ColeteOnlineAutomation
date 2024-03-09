package Base;

import PropertyUtility.PropertyFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverMethods
{
    private WebDriver driver;
    private WebDriverWait waitDriver;

    public void initiateDriver()
    {
        PropertyFile driverResource = new PropertyFile("DriverData/DriverResource");
        System.setProperty(driverResource.getValue("driverBrowser"), driverResource.getValue("location"));
        driver = new ChromeDriver();
        driver.get(driverResource.getValue("url"));
        driver.manage().window().maximize();
        //wait implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //explicit wait
        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(20, 1));
    }

    public void quitBrowser()
    {
        driver.quit();
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    public WebDriverWait getWaitDriver()
    {
        return waitDriver;
    }
}
