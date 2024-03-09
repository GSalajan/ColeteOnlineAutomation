package Pages;

import PropertyUtility.PropertyFile;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.InvalidParameterException;
import java.util.*;

public class NewOrderPage extends BasePage
{
    private enum CourierName
    {
        CARGUS("Cargus Economic Standard"),
        DPD("DPD Standard"),
        NEMO("Nemo Express"),
        DRAGONSTAR("DragonStar National"),
        FEDEX("FedEx (ex.TNT) Domestic Express"),
        GLS("GLS National"),
        SAMEDAY("Sameday 24H"),
        TCE("TCE Courier");

        public final String courierName;

        CourierName(String courierName)
        {
            this.courierName = courierName;
        }

        public static CourierName getCodeFromValue(String value) throws IllegalArgumentException {
            return Arrays.stream(CourierName.values())
                    .filter(val -> val.courierName.equals(value))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Unable to resolve CourierName: " + value));
        }
    }
    private HashMap<String, String> inputData;
    private HashMap<String, String> courierSpecsData;
    private List<Double> parcelsWeightsList = new ArrayList<>();
    private HashMap<String, List<String>> expectedErrorMessages = new HashMap<String, List<String>>();
    private boolean courierSelected = false;

    public NewOrderPage(WebDriver driver, WebDriverWait waitDriver, String resourcePath)
    {
        super(driver, waitDriver);

        PropertyFile inputDataFile = new PropertyFile(resourcePath);
        inputData = inputDataFile.getAllValues();

        PropertyFile courierSpecsFile = new PropertyFile("SpecificationsData/CourierSpecificationsResource");
        courierSpecsData = courierSpecsFile.getAllValues();
    }

    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@aria-label = 'Tara']")
    private WebElement sendersCountryElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'contactName']")
    private WebElement sendersNameElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'contactPhone']")
    private WebElement sendersPhone1Element;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'contactPhone2']")
    private WebElement sendersPhone2Element;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'contactCompany']")
    private WebElement sendersCompanyElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'contactEmail']")
    private WebElement sendersEmailElement;
    @FindBy(css = "span[class = 'login-text moveUp ng-star-inserted']")
    private WebElement emailAccountElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//mat-icon[@svgicon= 'copyUserEmail']")
    private WebElement copyUserEmailElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressPostalCode']")
    private WebElement sendersPostalCodeElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressCity']")
    private WebElement sendersCityElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressCounty']")
    private WebElement sendersCountyElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressStreet']")
    private WebElement sendersStreetElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressNumber']")
    private WebElement sendersStreetNoElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressBlock']")
    private WebElement sendersBlockElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressStair']")
    private WebElement sendersStairElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressIntercom']")
    private WebElement sendersIntercomElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressFloor']")
    private WebElement sendersFloorElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressApartment']")
    private WebElement sendersApartmentElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressLandmark']")
    private WebElement sendersLandmarkElement;
    @FindBy(xpath = "//app-order-address[@title = 'Expeditor']//input[@formcontrolname = 'addressAdditionalInfo']")
    private WebElement sendersExtraInfoElement;
    @FindBy(xpath = "//div[1][@role= 'tabpanel']//span[text() = 'Pasul urmator']")
    private WebElement nextStep1Element;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@aria-label = 'Tara']")
    private WebElement receiversCountryElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'contactName']")
    private WebElement receiversNameElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'contactPhone']")
    private WebElement receiversPhone1Element;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'contactPhone2']")
    private WebElement receiversPhone2Element;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'contactCompany']")
    private  WebElement receiversCompanyElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'contactEmail']")
    private WebElement receiversEmailElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressPostalCode']")
    private WebElement receiversPostalCodeElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressCity']")
    private WebElement receiversCityElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressCounty']")
    private WebElement receiversCountyElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressStreet']")
    private WebElement receiversStreetElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressNumber']")
    private WebElement receiversStreetNoElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressBlock']")
    private WebElement receiversBlockElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressStair']")
    private WebElement receiversStairElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressIntercom']")
    private WebElement receiversIntercomElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressFloor']")
    private WebElement receiversFloorElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressApartment']")
    private WebElement receiversApartmentElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressLandmark']")
    private WebElement receiversLandmarkElement;
    @FindBy(xpath = "//app-order-address[@title = 'Destinatar']//input[@formcontrolname = 'addressAdditionalInfo']")
    private WebElement receiversExtraInfoElement;
    @FindBy(xpath = "//div[2][@role= 'tabpanel']//span[text() = 'Pasul urmator']")
    private WebElement nextStep2Element;
    @FindBy(xpath = "//div[@class = 'mat-radio-label-content' and text()='Colet']")
    private WebElement parcelTypeElement;
    @FindBy(xpath = "//div[@class = 'mat-radio-label-content' and text()='Plic']")
    private WebElement envelopeTypeElement;
    @FindBy(xpath = "//input[@formcontrolname = 'count']")
    private WebElement numberOfParcelsElement;
    @FindBy(xpath = "//mat-error[@role = 'alert' and text() = ' Comanda trebuie sa contina minim 1 pachet ']")
    private WebElement minOneParcelErrorElement;
    @FindBy(xpath = "//app-notice[@type = 'warning']//div[contains(text(), ' Va rugam sa masurati si sa declarati exact ')]")
    private WebElement parcelsDimensionsWarningElement;
    @FindBy(xpath = "//input[@formcontrolname = 'content']")
    private WebElement parcelsContentElement;
    @FindBy(xpath = "//div[@formarrayname = 'packages']//mat-card[3]//span[1][@class ='package-specifications-text']")
    private WebElement totalWeightElement;
    @FindBy(xpath = "//div[@formarrayname = 'packages']//mat-card[3]//span[2][@class ='package-specifications-text']")
    private WebElement totalVolumeElement;
    @FindBy(xpath = "//div[@class = 'mat-radio-label-content' and text() = ' Voi printa AWB-ul si il voi lipi pe colet ']")
    private WebElement AWBPrintedElement;
    @FindBy(xpath = "//div[@class = 'mat-radio-label-content' and text() = ' Nu voi printa AWB-ul si acesta trebuie tiparit de curier ']")
    private WebElement noAWBPrintedElement;
    @FindBy(xpath = "//mat-radio-group[@formcontrolname = 'pickupOffset']//mat-radio-button[1]")
    private WebElement pickupFirstDayElement;
    @FindBy(xpath = "//mat-radio-group[@formcontrolname = 'pickupOffset']//mat-radio-button[2]")
    private WebElement pickupSecondDayElement;
    @FindBy(xpath = "//mat-radio-group[@formcontrolname = 'pickupOffset']//mat-radio-button[3]")
    private WebElement pickupThirdDayElement;
    @FindBy(xpath = "//mat-select[@formcontrolname='pickupTime']")
    private WebElement firstPickupHourElement;
    @FindBy(xpath = "//mat-select[@formcontrolname = 'lastPickupTime']")
    private WebElement lastPickupHourElement;
    @FindBy(xpath = "//mat-slide-toggle[@formcontrolname = 'openAtDestination']")
    private WebElement openAtDestinationElement;
    @FindBy(xpath = "//mat-slide-toggle[@formcontrolname = 'documentsReturn']")
    private WebElement documentsReturnElement;
    @FindBy(xpath = "//mat-slide-toggle[@formcontrolname = 'pickUpSMSNotify']")
    private WebElement pickupSMSNotifyElement;
    @FindBy(xpath = "//mat-error[@role = 'alert' and text() = 'Folositi doar cifre si . pentru zecimale']")
    private WebElement invalidRepaymentAmountElement;
    @FindBy(xpath = "//div[@class = 'no-repayment']")
    private WebElement noRepaymentElement;
    @FindBy(xpath = "//mat-slide-toggle[@formcontrolname= 'repaymentCash']")
    private WebElement repaymentCashElement;
    @FindBy(xpath = "//input[@formcontrolname= 'amount']")
    private WebElement repaymentAmountElement;
    @FindBy(xpath = "//mat-slide-toggle[@formcontrolname= 'repaymentInAccount']")
    private WebElement repaymentIntoAccountElement;
    @FindBy(xpath = "//input[@formcontrolname= 'bankName']")
    private WebElement accountOwnerElement;
    @FindBy(xpath = "//input[@formcontrolname= 'bankAccount']")
    private WebElement accountNumberElement;
    @FindBy(xpath = "//input[@formcontrolname= 'declaredValue']")
    private WebElement declaredValueElement;
    @FindBy(xpath = "//input[@formcontrolname= 'clientReference']")
    private WebElement clientReferenceElement;
    @FindBy(xpath = "//div[3][@role= 'tabpanel']//span[text() = 'Pasul urmator']")
    private WebElement nextStep3Element;
    @FindBy(xpath = "//div[@class = 'use-data-buttons']/button[1]")
    private WebElement copyInfoFromSenderElement;
    @FindBy(xpath = "//div[@class = 'use-data-buttons']/button[2]")
    private WebElement copyInfoFromReceiverElement;
    @FindBy(xpath = "//div[4][@role= 'tabpanel']//span[text() = 'Pasul urmator']")
    private WebElement nextStep4Element;

    public void fillSendersAddress()
    {
        checkSenderDefaultCountry();
        fillSendersName();
        fillSendersPhone1();
        //Telefon 2 - not mandatory
        //Companie - not mandatory
        fillSendersEmail();
        copySendersEmailFromAccount();
        javaScriptHelper.scrollDown("500");
        fillSendersPostalCode();
        //localitate -->verifica daca s-a completat corect, pe baza codului postal
        checkSendersCityIsFilledCorrectly();
        //judet/sector -->verifica daca s-a completat corect, pe baza codului postal
        checkSendersCountyIsFilledCorrectly();
        //strada -->verifica daca s-a completat corect, pe baza codului postal
        checkSendersStreetIsFilledCorrectly();
        fillSendersStreetNo();
        fillSendersBlock();
        fillSendersStair();
        fillSendersIntercom();
        fillSendersFloor();
        fillSendersApartment();
        fillSendersLandmark();
        fillSendersExtraInfo();
        javaScriptHelper.scrollToTheBottomOfThePage();
    }
    public void fillReceiversAddress()
    {
        checkReceiversDefaultCountry();
        javaScriptHelper.scrollDown("250");
        fillReceiversName();
        fillReceiversPhone1();
        fillReceiversPhone2();
        fillReceiversCompany();
        fillReceiversEmail();
        javaScriptHelper.scrollDown("500");
        fillReceiversPostalCode();
        checkReceiversCityIsFilledCorrectly();
        checkReceiversCountyIsFilledCorrectly();
        checkReceiversStreetIsFilledCorrectly();
        fillReceiversStreetNo();
        fillReceiversBlock();
        fillReceiversStair();
        fillReceiversIntercom();
        fillReceiversFloor();
        fillReceiversApartment();
        fillReceiversLandmark();
        fillReceiversExtraInfo();
        javaScriptHelper.scrollDown("600");
    }
    public void checkSenderDefaultCountry()
    {
        waitDriver.until(ExpectedConditions.elementToBeClickable(sendersCountryElement));
        Assert.assertEquals(inputData.get("SendersDefaultCountry"), elementHelper.getValue(sendersCountryElement));
    }
    public void fillSendersCountry()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersCountryElement)), inputData.get("SendersCountry"));
    }
    public void fillSendersName()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersNameElement)), inputData.get("SendersName"));
    }
    public void fillSendersPhone1()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersPhone1Element)), inputData.get("SendersPhone1"));
    }
    public void fillSendersPhone2()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersPhone2Element)), inputData.get("SendersPhone2"));
    }
    public void fillSendersCompany()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersCompanyElement)), inputData.get("SendersCompany"));
    }
    public void fillSendersEmail()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersEmailElement)), inputData.get("SendersEmail"));
    }
    public void copySendersEmailFromAccount()
    {
        elementHelper.click(copyUserEmailElement);
        elementHelper.clear(sendersEmailElement);
        elementHelper.fill(sendersEmailElement, elementHelper.getText(emailAccountElement));
    }
    public void fillSendersPostalCode()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersPostalCodeElement)), inputData.get("SendersPostalCode"));
    }
    public void fillSendersCity()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersCityElement)), inputData.get("SendersCity"));
    }
    public void checkSendersCityIsFilledCorrectly()
    {
        waitDriver.until(ExpectedConditions.attributeToBeNotEmpty(sendersCityElement, "value"));
        Assert.assertEquals(inputData.get("SendersDefaultCity"), elementHelper.getValue(sendersCityElement));
    }
    public void checkSendersCountyIsFilledCorrectly()
    {
        waitDriver.until(ExpectedConditions.elementToBeClickable(sendersCountyElement));
        Assert.assertEquals(inputData.get("SendersDefaultCounty"), elementHelper.getValue(sendersCountyElement));
    }
    public void fillSendersStreet()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersStreetElement)), inputData.get("SendersStreet"));
    }
    public void checkSendersStreetIsFilledCorrectly()
    {
        waitDriver.until(ExpectedConditions.elementToBeClickable(sendersStreetElement));
        Assert.assertEquals(inputData.get("SendersDefaultStreet"), elementHelper.getValue(sendersStreetElement));
    }
    public void fillSendersStreetNo()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersStreetNoElement)), inputData.get("SendersStreetNo"));
    }
    public void fillSendersBlock()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersBlockElement)), inputData.get("SendersBlock"));
    }
    public void fillSendersStair()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersStairElement)), inputData.get("SendersStair"));
    }
    public void fillSendersIntercom()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersIntercomElement)), inputData.get("SendersIntercom"));
    }
    public void fillSendersFloor()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersFloorElement)), inputData.get("SendersFloor"));
    }
    public void fillSendersApartment()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersApartmentElement)), inputData.get("SendersApartment"));
    }
    public void fillSendersLandmark()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersLandmarkElement)), inputData.get("SendersLandmark"));
    }
    public void fillSendersExtraInfo()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(sendersExtraInfoElement)), inputData.get("SendersExtraInfo"));
    }

    public void checkReceiversDefaultCountry()
    {
        waitDriver.until(ExpectedConditions.elementToBeClickable(receiversCountryElement));
        Assert.assertEquals(inputData.get("ReceiversDefaultCountry"), elementHelper.getValue(receiversCountryElement));
    }
    public void fillReceiversName()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversNameElement)), inputData.get("ReceiversName"));
    }
    public void fillReceiversPhone1()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversPhone1Element)), inputData.get("ReceiversPhone1"));
    }
    public void fillReceiversPhone2()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversPhone2Element)), inputData.get("ReceiversPhone2"));
    }
    public void fillReceiversCompany()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversCompanyElement)), inputData.get("ReceiversCompany"));
    }
    public void fillReceiversEmail()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversEmailElement)), inputData.get("ReceiversEmail"));
    }
    public void fillReceiversPostalCode()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversPostalCodeElement)), inputData.get("ReceiversPostalCode"));
    }
    public void checkReceiversCityIsFilledCorrectly()
    {
        waitDriver.until(ExpectedConditions.attributeToBeNotEmpty(receiversCityElement, "value"));
        Assert.assertEquals(inputData.get("ReceiversDefaultCity"), elementHelper.getValue(receiversCityElement));
    }
    public void checkReceiversCountyIsFilledCorrectly()
    {
        waitDriver.until(ExpectedConditions.elementToBeClickable(receiversCountyElement));
        Assert.assertEquals(inputData.get("ReceiversDefaultCounty"), elementHelper.getValue(receiversCountyElement));
    }
    public void checkReceiversStreetIsFilledCorrectly()
    {
        waitDriver.until(ExpectedConditions.elementToBeClickable(receiversStreetElement));
        Assert.assertEquals(inputData.get("ReceiversDefaultStreet"), elementHelper.getValue(receiversStreetElement));
    }
    public void fillReceiversStreetNo()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversStreetNoElement)), inputData.get("ReceiversStreetNo"));
    }
    public void fillReceiversBlock()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversBlockElement)), inputData.get("ReceiversBlock"));
    }
    public void fillReceiversStair()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversStairElement)), inputData.get("ReceiversStair"));
    }
    public void fillReceiversIntercom()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversIntercomElement)), inputData.get("ReceiversIntercom"));
    }
    public void fillReceiversFloor()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversFloorElement)), inputData.get("ReceiversFloor"));
    }
    public void fillReceiversApartment()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversFloorElement)), inputData.get("ReceiversApartment"));
    }
    public void fillReceiversLandmark()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversLandmarkElement)), inputData.get("ReceiversLandmark"));
    }
    public void fillReceiversExtraInfo()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(receiversExtraInfoElement)), inputData.get("ReceiversExtraInfo"));
    }

    public void fillParcelsInfo()
    {
        javaScriptHelper.waitForCompetePageToBeLoaded();
        clickSendingType();
        fillParcelsContent();

        if (inputData.get("SendingType").equals("Parcel"))
        {
            fillNumberOfParcels();
            javaScriptHelper.scrollDown("250");
            fillParcelsDimensions();
            if (Integer.valueOf(inputData.get("NumberOfParcels")) > 1)
                checkTotalWeight();
        }
        javaScriptHelper.scrollDown("500");

        clickAWBOptions();
        clickPickupDay();
        selectFirstPickupHour();
        selectLastPickupHour();
        javaScriptHelper.scrollDown("700");
        clickOpenAtDelivery();
        clickDocumentsReturn();
        clickPickupSMSNotify();
        fillRepaymentType();

        fillClientReference();
    }
    public void clickSendingType()
    {
        if (inputData.get("SendingType").equals("Envelope"))
        {
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(envelopeTypeElement)));
            //Assert.assertFalse(numberOfParcelsElement.isDisplayed());
        }
        else if (inputData.get("SendingType").equals("Parcel"))
        {
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(parcelTypeElement)));
            //Assert.assertTrue(numberOfParcelsElement.isDisplayed());
        }
        else
            throw new InvalidParameterException("Invalid sending type");

    }
    public void fillNumberOfParcels()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(numberOfParcelsElement)), inputData.get("NumberOfParcels"));
    }
    public void fillParcelsContent()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(parcelsContentElement)), inputData.get("ParcelsContent"));
    }
    public void clickAWBOptions()
    {
        if (inputData.get("AWBPrinted").equals("YES"))
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(AWBPrintedElement)));
        else if (inputData.get("AWBPrinted").equals("NO"))
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(noAWBPrintedElement)));
        else throw new InvalidParameterException("Invalid value for AWBPrinted - " + inputData.get("AWBPrinted"));
    }
    public void clickPickupDay()
    {
        if (inputData.get("PickupDay").equals("FirstDay"))
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(pickupFirstDayElement)));
        else if (inputData.get("PickupDay").equals("SecondDay"))
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(pickupSecondDayElement)));
        else if (inputData.get("PickupDay").equals("ThirdDay"))
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(pickupThirdDayElement)));
        else throw new InvalidParameterException("Invalid value for PickupDay - " + inputData.get("PickupDay"));
    }
    public void selectFirstPickupHour()
    {
        elementHelper.click(firstPickupHourElement);
        List<WebElement> firstPickupHourElements = driver.findElements(By.xpath("//div[@role='listbox']//span[@class = 'mat-option-text']"));

        for (Integer i = 0; i < firstPickupHourElements.size(); i++)
        {
            if (firstPickupHourElements.get(i).getText().equals(inputData.get("FirstPickupHour")))
            {
                firstPickupHourElements.get(i).click();
                break;
            }
        }
    }
    public void selectLastPickupHour()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elementHelper.click(lastPickupHourElement);
        List<WebElement> lastPickupHourElements = driver.findElements(By.xpath("//div[@role='listbox']//span[@class = 'mat-option-text']"));

        int size = lastPickupHourElements.size();
        for (Integer i = 0; i < size; i++)
        {
            if (lastPickupHourElements.get(i).getText().equals(inputData.get("LastPickupHour")))
            {
                lastPickupHourElements.get(i).click();
                break;
            }
        }
    }
    public void clickOpenAtDelivery()
    {
        if (inputData.get("OpenAtDelivery").equals("YES"))
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(openAtDestinationElement)));
    }
    public void clickDocumentsReturn()
    {
        if (inputData.get("DocumentsReturn").equals("YES"))
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(documentsReturnElement)));
    }
    public void clickPickupSMSNotify()
    {
        if (inputData.get("PickupSMSNotify").equals("YES"))
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(pickupSMSNotifyElement)));
    }
    public void fillRepaymentType()
    {
        switch (inputData.get("Repayment"))
        {
            case "NoRepayment":
            {
                elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(noRepaymentElement)));
                fillDeclaredValue();

                break;
            }
            case "RepaymentCash":
            {
                elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(repaymentCashElement)));
                elementHelper.clear(repaymentAmountElement);
                elementHelper.fill(waitDriver.until(ExpectedConditions.visibilityOf(repaymentAmountElement)), inputData.get("RepaymentAmount"));

                break;
            }
            case "RepaymentIntoAccount":
            {
                elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(repaymentIntoAccountElement)));
                elementHelper.fill(waitDriver.until(ExpectedConditions.visibilityOf(repaymentAmountElement)), inputData.get("RepaymentAmount"));
                elementHelper.fill(waitDriver.until(ExpectedConditions.visibilityOf(accountOwnerElement)), inputData.get("AccountOwner"));
                elementHelper.fill(waitDriver.until(ExpectedConditions.visibilityOf(accountNumberElement)), inputData.get("AccountNumber"));

                break;
            }
            default:
                throw new InvalidParameterException("Invalid repayment method" + inputData.get("Repayment"));
        }
    }
    public void fillDeclaredValue()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(declaredValueElement)), inputData.get("DeclaredValue"));
    }
    public void fillClientReference()
    {
        elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(clientReferenceElement)), inputData.get("ClientReference"));
    }

    public void fillParcelsDimensions()
    {
        for (Integer counter = 1; counter <= Integer.valueOf(inputData.get("NumberOfParcels")); counter++)
        {
            //greutate colet
            elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@formarrayname = 'packages']//mat-card[" + counter + "]//input[@formcontrolname = 'weight']"))), inputData.get("WeightParcel" + counter));
            parcelsWeightsList.add(Double.valueOf(inputData.get("WeightParcel" + counter)));
            //lungime colet
            elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@formarrayname = 'packages']//mat-card[" + counter + "]//input[@formcontrolname = 'xLength']"))),inputData.get("LengthParcel" + counter));
            //latime colet
            elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@formarrayname = 'packages']//mat-card[" + counter + "]//input[@formcontrolname = 'yLength']"))),inputData.get("WidthParcel" + counter));
            //inaltime colet
            elementHelper.fill(waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@formarrayname = 'packages']//mat-card[" + counter + "]//input[@formcontrolname = 'zLength']"))),inputData.get("HeightParcel" + counter));

            //volum colet 1 - check if disabled (are atribut "disabled"), check the value
            String volume = elementHelper.getValue(driver.findElement(By.xpath("//div[@formarrayname = 'packages']//mat-card[1]//input[@formcontrolname = 'volume']")));
            Assert.assertEquals((Double.valueOf(inputData.get("LengthParcel" + counter)) * Double.valueOf(inputData.get("WidthParcel" + counter)) * Double.valueOf(inputData.get("HeightParcel" + counter)))/1000000, Double.parseDouble(volume), 0.001);
        }
    }

    public void checkTotalWeight()
    {
        String actualTotalWeight = elementHelper.getValue(waitDriver.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@formarrayname = 'packages']//mat-card[3]//span[1][@class ='package-specifications-text']"))));
        double expectedTotalWeight = parcelsWeightsList.stream().mapToDouble(i -> i).sum();
        Assert.assertEquals(expectedTotalWeight, Double.parseDouble(actualTotalWeight), 0.001);
    }



    public Integer getTabsNumber()
    {
        List<WebElement> tabs = driver.findElements(By.xpath("//div[contains(@class, 'mat-horizontal-stepper-header-container')]/mat-step-header"));
        return tabs.size();
    }
    public void clickCopyBillingInfo()
    {
        if (inputData.get("CopyBillingInfoFrom").equals("Sender"))
            elementHelper.click(copyInfoFromSenderElement);
        else if (inputData.get("CopyBillingInfoFrom").equals("Receiver"))
            elementHelper.click(copyInfoFromReceiverElement);
        else
            throw new InvalidParameterException("Invalid value for CopyBillingInfoFrom - " + inputData.get("CopyBillingInfoFrom"));

        //todo
        //checkDateFacturare();

        //js.scrollToTheBottomOfThePage();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickNextStep(Integer step)
    {
        //elementHelper.hover(driver, nextStep4Element);
        switch (step)
        {
            case 1:
            {
                elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(nextStep1Element)));
                break;
            }
            case 2:
            {
                elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(nextStep2Element)));
                break;
            }
            case 3:
            {
                elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(nextStep3Element)));

                //error messages
                try
                {
                    if (inputData.get("SendingType").equals("Parcel"))
                        if (Integer.parseInt(inputData.get("NumberOfParcels")) <= 0)
                            Assert.assertTrue(minOneParcelErrorElement.isDisplayed());
                }
                catch (NumberFormatException e)
                {
                    Assert.assertTrue(minOneParcelErrorElement.isDisplayed());
                }

                try
                {
                    if (!(inputData.get("Repayment").equals("NoRepayment")))
                        if (!(Double.valueOf(inputData.get("RepaymentAmount")) > 0))
                            Assert.assertTrue(invalidRepaymentAmountElement.isDisplayed());
                }
                catch (NullPointerException | NumberFormatException e)
                {
                    Assert.assertTrue(invalidRepaymentAmountElement.isDisplayed());
                }

                break;
            }
            case 4:
            {
                elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(nextStep4Element)));
                break;
            }
            default:
                throw new InvalidParameterException("Invalid next step" + step);
        }

    }
    public void validateCouriersOffer()
    {
        //get the 8-th forms with courier's info
        List<WebElement> couriersList = driver.findElements(By.xpath("//app-order-courier"));
        for(WebElement courier: couriersList)
        {
            //foreach courier form, detect the container with the details
            WebElement courierContainer = courier.findElement(By.xpath(".//div[@class='show-data']"));

            //get ALL the children elements of the container form --> courier's details
            List<WebElement> courierDetailsList = courierContainer.findElements(By.xpath("./child::*"));

            //detect the courier's name
            CourierName courierName = CourierName.getCodeFromValue(courierContainer.findElement(By.xpath(".//div[@class = 'courier-name']//div[@class = 'text']")).getText().replace("\n", " "));
            Boolean courierAvailable = checkIfCourierIsAvailable(courierName.toString());

            //foreach detail element, check if shows the correct data
            for (WebElement element : courierDetailsList)
            {
                switch (courierName)
                {
                    case CARGUS:
                    case DPD:
                    case NEMO:
                    case TCE:
                    case DRAGONSTAR:
                    case FEDEX:
                    case GLS:
                    case SAMEDAY:
                    {
                        checkWebElement(courierName.toString(), element, courierAvailable);
                        break;
                    }
                    default:
                        throw new EnumConstantNotPresentException(CourierName.class, courierName.toString());
                }

            }

            //check error messages
            for (String key: expectedErrorMessages.keySet())
            {
                WebElement errorsElement = driver.findElement(
                        By.xpath("//div[@class = 'text' and contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), '" + key + "')]/../../../..//div[@class = 'errors ng-star-inserted']"));

                List<WebElement> actualErrorElements = errorsElement.findElements(By.xpath("./child::*"));
                List<String> expectedErrorMessagesList = expectedErrorMessages.get(key);

                boolean errorFound = false;
                for(String error: expectedErrorMessagesList)
                {
                    errorFound = false;
                    for (WebElement errorElement: actualErrorElements)
                    {
                        if (error.equals(elementHelper.getText(errorElement)))
                        {
                            errorFound = true;
                            break;
                        }
                    }
                    Assert.assertTrue("Eroarea <<" + error + ">> pentru curierul " + key + " nu este afisata corespunzator.", errorFound);
                }
            }
        }
    }

    public void chooseCourier()
    {
        if (courierSelected)
        {
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[1][@class='rules-checkbox']"))));
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[2][@class='rules-checkbox']"))));
            elementHelper.click(waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), ' Plateste comanda ')]"))));
        }
    }

    public boolean checkIfCourierIsAvailable(String courier)
    {
        List<String> expectedErrorMessagesList = new ArrayList<>();
        Optional overweight = parcelsWeightsList.stream()
                        .filter((Double greutate) -> {return greutate > Double.valueOf(courierSpecsData.get(courier + "_ParcelsMaxKg"));})
                        .findAny();

        if (overweight.isPresent())
            expectedErrorMessagesList.add(courierSpecsData.get(courier + "_OverweightErrorMessage"));

        if (courierSpecsData.get(courier + "_MandatoryPrintedAWB").equals("YES") && inputData.get("AWBPrinted").equals("NO"))
            expectedErrorMessagesList.add(courierSpecsData.get("MandatoryAWBErrorMessage"));

        if (courierSpecsData.get(courier + "_AllowsOpenAtDelivery").equals("NO") && inputData.get("OpenAtDelivery").equals("YES"))
            expectedErrorMessagesList.add(courierSpecsData.get("OpenAtDeliveryErrorMessages"));

        if (courierSpecsData.get(courier + "_AllowsDocumentsReturn").equals("NO") && inputData.get("DocumentsReturn").equals("YES"))
            expectedErrorMessagesList.add(courierSpecsData.get("DocumentsReturnErrorMessages"));

        if (courierSpecsData.get(courier + "_AllowsCashRepayment").equals("NO") && inputData.get("Repayment").equals("RepaymentCash"))
            expectedErrorMessagesList.add(courierSpecsData.get("CashRepaymentErrorMessages"));

        if (inputData.get("Repayment").equals("NoRepayment") && courierSpecsData.get(courier + "_AllowsParcelsInsurance").equals("NO") && Double.valueOf(inputData.get("DeclaredValue")) > 0)
            expectedErrorMessagesList.add(courierSpecsData.get("ParcelsInsuranceErrorMessages"));

        expectedErrorMessages.put(courier, expectedErrorMessagesList);

        //inputData.get("PickupDay").equals("SecondDay") ||
        if (courier.equals("SAMEDAY") && ( inputData.get("PickupDay").equals("ThirdDay")))
            return false;

        return (expectedErrorMessagesList.size() > 0 ? false : true);
    }

    private void checkWebElement(String courier, WebElement element, boolean courierAvailable)
    {
        //check if the checkbox is correctly enabled/disabled
        if (element.getAttribute("class").equals("checkbox"))
        {
            if (courierAvailable)
            {
                Assert.assertTrue("The checkbox must be enabled for " + courier, element.findElement(By.xpath(".//input[@type = 'checkbox']")).isEnabled());
                if (!courierSelected)
                {
                    elementHelper.click(element);
                    courierSelected = true;
                }
            }
            else
                Assert.assertTrue("The checkbox must be disabled for  " + courier, !element.findElement(By.xpath(".//input[@type = 'checkbox']")).isEnabled());
        }
        else if (element.getAttribute("class").equals("courier-price"))
        {
            WebElement priceNoTVAElement = element.findElement(By.xpath(".//div[@class = 'price-no-tva']"));
            WebElement fullPriceElement = element.findElement(By.xpath(".//div[@class = 'price']"));
            WebElement priceNotAvailableElement = element.findElement(By.xpath("./div[@class = 'price-not-available']"));

            if (courierAvailable)
            {
                Assert.assertTrue("Price without TVA must be displayed for " + courier, priceNoTVAElement.isDisplayed());
                Assert.assertTrue("Price with TVA must be displayed for " + courier, fullPriceElement.isDisplayed());
                Assert.assertTrue("Prices should not be displayed for " + courier, !priceNotAvailableElement.isDisplayed());
            }
            else
            {
                if (!courier.equals("SAMEDAY"))
                {
                    Assert.assertTrue("Price without TVA should not be displayed for " + courier, !priceNoTVAElement.isDisplayed());
                    Assert.assertTrue("Price with TVA should not be displayed for " + courier, !fullPriceElement.isDisplayed());
                    Assert.assertTrue(" Message should be displayed " + courier, priceNotAvailableElement.isDisplayed());
                    Assert.assertEquals("todo..." + courier, inputData.get("NotAvailableMessage"), priceNotAvailableElement.getText().replace("\n", " "));
                }
            }
        }
        else if (element.getAttribute("class").equals("info-text"))
        {
            WebElement maxKgElement = element.findElement(By.xpath("./div[@class='max-kg']"));
            Assert.assertEquals("Wrong max kilos for " + courier, courierSpecsData.get(courier + "_ParcelsMaxKgMessage"), maxKgElement.getText().replace("\n", " "));
            WebElement deliveryInXDaysElement = element.findElement(By.xpath("./div[1]"));
            Assert.assertEquals("Wrong delivery no. of days for " + courier, courierSpecsData.get(courier + "_DeliveryInXDays"), deliveryInXDaysElement.getText().replace("\n", " "));
        }
        else if (element.getAttribute("class").equals("icons"))
        {
            boolean deliveryOnSaturdayDisabled = element.findElement(By.xpath(".//mat-icon[@svgicon='saturday']/parent::span")).getAttribute("class").equals("not-available");
            Assert.assertTrue("AllowsDeliveryOnSaturday " + courier,
                    (courierSpecsData.get(courier + "_AllowsDeliveryOnSaturday").equals("YES") ? !deliveryOnSaturdayDisabled : deliveryOnSaturdayDisabled));

            boolean mandatoryPrintedAWBDisabled = element.findElement(By.xpath(".//mat-icon[@svgicon='printer']/parent::span")).getAttribute("class").equals("printer not-available");
            Assert.assertTrue("Printing AWB is mandatory for " + courier,
                    (courierSpecsData.get(courier + "_MandatoryPrintedAWB").equals("YES") ? !mandatoryPrintedAWBDisabled : mandatoryPrintedAWBDisabled));

            boolean cashRepaymentDisabled = element.findElement(By.xpath(".//mat-icon[@svgicon='courierRepaymentCash']/parent::span")).getAttribute("class").equals("not-available");;
            Assert.assertTrue("Ramburs cash " + courier,
                    (courierSpecsData.get(courier + "_AllowsCashRepayment").equals("YES") ? !cashRepaymentDisabled : cashRepaymentDisabled));

            boolean repaymentIntoAccountDisabled = element.findElement(By.xpath(".//mat-icon[@svgicon='credit-card']/parent::span")).getAttribute("class").equals("not-available");
            Assert.assertTrue("Repayment into account " + courier,
                    (courierSpecsData.get(courier + "_AllowsCashIntoAccount").equals("YES") ? !repaymentIntoAccountDisabled : repaymentIntoAccountDisabled));

            boolean insuranceDisabled = element.findElement(By.xpath(".//mat-icon[@svgicon='insurance']/parent::span")).getAttribute("class").equals("not-available");
            Assert.assertTrue("Parcel's insurance " + courier,
                    (courierSpecsData.get(courier + "_AllowsParcelsInsurance").equals("YES") ? !insuranceDisabled : insuranceDisabled));

            boolean openAtDeliveryDisabled = element.findElement(By.xpath(".//mat-icon[@svgicon='open-on-delivery']/parent::span")).getAttribute("class").equals("border not-available");;
            Assert.assertTrue("Open at delivery " + courier,
                    (courierSpecsData.get(courier + "_AllowsOpenAtDelivery").equals("YES") ? !openAtDeliveryDisabled : openAtDeliveryDisabled));

            WebElement lastOrderHourForTodayElement = element.findElement(By.xpath(".//div[@class='last-order-hour']"));
            Assert.assertEquals(courierSpecsData.get(courier + "_LastOrderHourForToday"), lastOrderHourForTodayElement.getText());
            WebElement lastPickUpHourForTodayElement = element.findElement(By.xpath(".//div[@class='last-pick-up-hour']"));
            Assert.assertEquals(courierSpecsData.get(courier + "_LastPickUpHourForToday"), lastPickUpHourForTodayElement.getText());
        }

        //if checkbox not enabled, check messages with the cause of unavailability
    }

    public void validateSendersAddressErrorMessages()
    {
        elementHelper.click(sendersApartmentElement);
        WebElement countryErrorElement = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Expeditor']/..//form//h5[text()='Tara']/..//mat-error")));
        Assert.assertEquals(inputData.get("CountryErrorMessage"), countryErrorElement.getText());

        WebElement contactPhoneErrorElement = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Expeditor']/..//input[@formcontrolname = 'contactPhone']/../../..//mat-error")));
        Assert.assertEquals(inputData.get("ContactPhoneErrorMessage"), contactPhoneErrorElement.getText());

        WebElement contactPhone2ErrorElement = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Expeditor']/..//input[@formcontrolname = 'contactPhone2']/../../..//mat-error")));
        Assert.assertEquals(inputData.get("ContactPhone2ErrorMessage"), contactPhone2ErrorElement.getText());

        WebElement contactCompanyErrorElement = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Expeditor']/..//input[@formcontrolname = 'contactCompany']/../../..//mat-error")));
        Assert.assertEquals(inputData.get("ContactCompanyErrorMessage"), contactCompanyErrorElement.getText());

        WebElement emailErrorElement = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Expeditor']/..//input[@formcontrolname = 'contactEmail']/../../..//mat-error")));
        Assert.assertEquals(inputData.get("EmailErrorMessage"), emailErrorElement.getText());

        WebElement cityErrorElement = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Expeditor']/..//input[@formcontrolname = 'addressCity']/../../..//mat-error")));
        if (sendersCityElement.getText().length() == 0)
            Assert.assertEquals(inputData.get("EmptyCityErrorMessage"), cityErrorElement.getText());
        else
            Assert.assertEquals(inputData.get("InvalidCityErrorMessage"), cityErrorElement.getText());

        WebElement streetErrorElement = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Expeditor']/..//input[@formcontrolname = 'addressStreet']/../../..//mat-error")));
        Assert.assertEquals(inputData.get("EmptyStreetErrorMessage"), streetErrorElement.getText());

        WebElement postalCodeErrorElement = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Expeditor']/..//input[@formcontrolname = 'addressPostalCode']/../../..//mat-error")));
        Assert.assertEquals(inputData.get("InvalidCountryAndPostalCodeErrorMessage"), postalCodeErrorElement.getText());
    }
}
