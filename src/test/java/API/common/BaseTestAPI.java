package API.common;

import Website.listeners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTestAPI {
    @BeforeMethod
    public static void setupEnvironment(){}
}
