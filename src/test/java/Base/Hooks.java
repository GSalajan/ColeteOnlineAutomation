package Base;

import org.junit.After;
import org.junit.Before;

public class Hooks extends DriverMethods
{

    @Before
    public void prepareEnvironment()
    {
        initiateDriver();
    }

    @After
    public void cleanEnvironment()
    {
        //quitBrowser();
    }
}
