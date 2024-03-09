package Tests;

import Base.Hooks;
import Pages.LoginPage;
import org.junit.Test;

public class LoginWithEmailTest extends Hooks
{
    @Test
    //Caz 1: autentificare de la butonul Autentificare din coltul dreapta al paginii
    public void LoginWithEmailAutentificareTest()
    {
        LoginPage loginPage = new LoginPage(getDriver(), getWaitDriver(), "TestData/LoginWithEmailTestResource");
        loginPage.loginWithEmailAutentificare();
    }

    @Test
    //Caz 2: autentificare, ca prim pas a unei comenzi noi (butonul Comanda noua din stanga paginii de start)
    public void LoginWithEmailComandaNouaTest()
    {
        LoginPage loginPage = new LoginPage(getDriver(), getWaitDriver(), "TestData/LoginWithEmailTestResource");
        loginPage.loginWithEmailForNewOrder();
    }

    @Test
    //Caz 3: autentificare ca prim pas al unei comenzi noi (de la butonul Comanda acum din mijlocul paginii)
    public void LoginWithEmailComandaAcumTest()
    {
        LoginPage loginPage = new LoginPage(getDriver(), getWaitDriver(), "TestData/LoginWithEmailTestResource");
        loginPage.loginWithEmailComandaAcum();
    }
}
