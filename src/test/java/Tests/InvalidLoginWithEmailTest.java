package Tests;

import Base.Hooks;
import Pages.LoginPage;
import org.junit.Test;

public class InvalidLoginWithEmailTest extends Hooks
{
    @Test
    //autentificare de la butonul Autentificare din coltul dreapta al paginii
    public void LoginWithEmailAutentificareTest()
    {
        LoginPage loginPage = new LoginPage(getDriver(), getWaitDriver(), "TestData/InvalidLoginWithEmailTestResource");
        loginPage.loginWithEmailAutentificare();
        loginPage.checkLoginErrorMessages();
    }
}
