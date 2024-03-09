package Pages;

import PropertyUtility.PropertyFile;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class LoginPage extends BasePage
{
    private HashMap<String, String> inputData;

    public LoginPage(WebDriver driver, WebDriverWait waitDriver, String resourcePath)
    {
        super(driver, waitDriver);

        PropertyFile testData = new PropertyFile(resourcePath);
        //se pun toate valorile din fisier intr-un hashmap
        inputData = testData.getAllValues();
    }

    @FindBy(css = "input[formcontrolname= 'email']")
    private WebElement emailElement;
    @FindBy(css="input[formcontrolname= 'password']")
    private  WebElement passwordElement;
    @FindBy(xpath = "//span[contains(text(), ' Autentifica ')]")
    private WebElement autentificaElement;
    @FindBy(css = "a[class = 'here']")
    private WebElement aiciLinkElement;
    @FindBy(css = "mat-icon[svgicon = 'facebookLogo']")
    private WebElement autentificareCuFacebookElement;
    @FindBy(css = "div[class = 'error-message']")
    private WebElement invalidLoginMessageElement;

    public void fillEmail(String email)
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(emailElement)), email);
    }
    public void fillPassword(String password)
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(passwordElement)), password);
    }
    public void clickAutentifica()
    {
        elementHelper.click(autentificaElement);
    }

    public void clickAiciLink()
    {
        elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(aiciLinkElement)));
        javaScriptHelper.waitForCompetePageToBeLoaded();
    }

    public void clickAutentificareCuFacebook()
    {
        elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(autentificareCuFacebookElement)));
    }

    private void loginWithEmail()
    {
        fillEmail(inputData.get("email"));
        fillPassword(inputData.get("password"));
        clickAutentifica();
    }

    public void loginWithEmailAutentificare()
    {
        HomePage homePage = new HomePage(driver, waitDriver);
        homePage.clickAutentificare();
        homePage.clickCloseCookie();

        loginWithEmail();
    }

    public void loginWithEmailComandaAcum()
    {
        HomePage homePage = new HomePage(driver, waitDriver);
        homePage.clickComandaAcum();
        homePage.clickCloseCookie();

        //Daca aveti deja un cont, va puteti autentifica aici
        clickAiciLink();

        waitDriver.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("a[class = 'here']"))));
        loginWithEmail();
    }

    public void loginWithEmailForNewOrder()
    {
        HomePage homePage = new HomePage(driver, waitDriver);
        homePage.clickComandaNoua();
        homePage.clickCloseCookie();

        //Daca aveti deja un cont, va puteti autentifica aici
        clickAiciLink();

        waitDriver.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("a[class = 'here']"))));
        loginWithEmail();
    }

    public void checkLoginErrorMessages()
    {
        elementHelper.waitElementTextToBePresent(driver, invalidLoginMessageElement, inputData.get("invalidLoginErrorMessage"));
        Assert.assertEquals(inputData.get("invalidLoginErrorMessage"), elementHelper.getText(invalidLoginMessageElement));
    }
}
