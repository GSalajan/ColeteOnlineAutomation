package Tests;

import Base.Hooks;
import Pages.LoginPage;
import Pages.NewOrderPage;
import org.junit.Test;

public class NewOrderTest1 extends Hooks
{
    @Test
    public void NewOrderTest1()
    {
        LoginPage loginPage = new LoginPage(getDriver(), getWaitDriver(), "TestData/LoginWithEmailTestResource");
        NewOrderPage newOrderPage = new NewOrderPage(getDriver(), getWaitDriver(), "TestData/NewOrderTest1Resource");

        //login
        loginPage.loginWithEmailForNewOrder();

        //sender's address
        newOrderPage.fillSendersAddress();
        newOrderPage.clickNextStep(1);

        //receiver's address
        newOrderPage.fillReceiversAddress();
        newOrderPage.clickNextStep(2);

        //parcel's info
        newOrderPage.fillParcelsInfo();
        newOrderPage.clickNextStep(3);

        //billing info
        if (newOrderPage.getTabsNumber() == 5) {
            newOrderPage.clickCopyBillingInfo();
            newOrderPage.clickNextStep(4);
        }

        //couriers list
        newOrderPage.validateCouriersOffer();
        newOrderPage.chooseCourier();
    }
}
