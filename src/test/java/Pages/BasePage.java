package Pages;

import Helper.ElementHelper;
import Helper.JavaScriptHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage
{
    public WebDriver driver;
    public WebDriverWait waitDriver;
    public ElementHelper elementHelper;
    public JavaScriptHelper javaScriptHelper;

    public BasePage(WebDriver driver, WebDriverWait waitDriver){
        this.driver = driver;
        this.waitDriver = waitDriver;
        elementHelper = new ElementHelper();
        javaScriptHelper = new JavaScriptHelper(driver);
        PageFactory.initElements(driver, this);
    }
}
