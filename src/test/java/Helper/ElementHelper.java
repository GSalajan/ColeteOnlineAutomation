package Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementHelper
{
    public ElementHelper()
    {
    }

    public void click(WebElement element)
    {
        element.click();
    }

    public void hover(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void fill(WebElement element, String value)
    {
        element.sendKeys(value);
    }

    public void clear(WebElement element)
    {
        element.clear();
    }

    public String getText(WebElement element)
    {
        return element.getText();
    }

    public String getValue(WebElement element)
    {
        return element.getAttribute("value");
    }

    public void selectByIndex(WebElement element, Integer index)
    {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectByFirstIndex(WebElement element)
    {
        Select select = new Select(element);
        select.selectByIndex(0);
    }

    public void selectByLastIndex(WebElement element)
    {
        Select select = new Select(element);
        select.selectByIndex(select.getOptions().size() - 1);
    }

    public void waitElementTextToBePresent(WebDriver driver, WebElement element, String value)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.textToBePresentInElement(element, value));
    }

}
