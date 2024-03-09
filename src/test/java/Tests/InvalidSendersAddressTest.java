package Tests;

import Base.Hooks;
import Pages.LoginPage;
import Pages.NewOrderPage;
import org.junit.Test;

public class InvalidSendersAddressTest extends Hooks
{
    @Test
    public void InvalidSendersAddressTest()
    {
        LoginPage loginPage = new LoginPage(getDriver(), getWaitDriver(), "TestData/LoginWithEmailTestResource");
        NewOrderPage newOrderPage = new NewOrderPage(getDriver(), getWaitDriver(), "TestData/InvalidSendersAddressTestResource");

        loginPage.loginWithEmailComandaAcum();
        newOrderPage.fillSendersCountry();
        newOrderPage.fillSendersName();
        newOrderPage.fillSendersPhone1();
        newOrderPage.fillSendersPhone2();
        newOrderPage.fillSendersCompany();
        newOrderPage.fillSendersEmail();
        newOrderPage.fillSendersCity();
        newOrderPage.fillSendersStreet();

        newOrderPage.fillSendersPostalCode();
        newOrderPage.validateSendersAddressErrorMessages();
    }
}
