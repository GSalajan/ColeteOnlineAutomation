package Helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptHelper
{
    public JavascriptExecutor js;

    public JavaScriptHelper(WebDriver driver)
    {
        js = (JavascriptExecutor)driver;
    }

    public void scrollDown(String numberOfPixels)
    {
        js.executeScript("scroll(0, " + numberOfPixels + ");");
    }

    public void scrollToTheBottomOfThePage()
    {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void waitForCompetePageToBeLoaded()
    {
        js.executeScript("return document.readyState").toString().equals("complete");
    }
}
