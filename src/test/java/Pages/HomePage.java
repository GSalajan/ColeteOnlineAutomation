package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage
{
    public HomePage(WebDriver driver, WebDriverWait waitDriver)
    {
        super(driver, waitDriver);
    }

    @FindBy(xpath = "//span[contains(text(), 'Autentificare')]")
    private WebElement autentificareElement;
    @FindBy(xpath = "//div[@class = 'menu-buttons']/a/span[contains(text(), 'Comanda noua')]")
    private WebElement comandaNouaElement;
    @FindBy(xpath = "//span[contains(text(), ' Comanda acum ')]")
    private WebElement comandaAcumElement;
    @FindBy(xpath = "//cookie-law-component//a")
    private WebElement closeCookieElement;

    public void clickAutentificare()
    {
        elementHelper.click(autentificareElement);
    }

    public void clickComandaNoua()
    {
        elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(comandaNouaElement)));
    }

    public void clickComandaAcum()
    {
        elementHelper.click(comandaAcumElement);
    }

    public void clickCloseCookie()
    {
        elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(closeCookieElement)));
    }
}
