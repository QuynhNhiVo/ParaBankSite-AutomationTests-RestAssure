package drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager(){}

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quit(){
        if (DriverManager.driver.get() != null) {
            DriverManager.driver.get().quit();
            driver.remove();
        }
    }
}
