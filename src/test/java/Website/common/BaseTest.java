package Website.common;

import constants.ConfigData;
import drivers.DriverManager;
import Website.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;
import utils.LogUtils;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("edge") String browser) {
        WebDriver driver = setupBrowser(browser);
//        if (ConfigData.FULLSCREEN.equals("true")) {
//            LogUtils.info("Maximize windows");
//            driver.manage().window().maximize();
//        }
        DriverManager.setDriver(driver);
    }

    @AfterMethod
    public void closeDriver(){
        DriverManager.quit();
    }

    public WebDriver setupBrowser(String browser) {
        WebDriver driver;
        switch (browser.trim().toLowerCase()) {
            case "edge" -> {
                driver = initMSEdge();
                break;
            }
            case "chrome" -> {
                driver = initGGChrome();
                break;
            }
            default -> driver = initMSEdge();
        }
        if (ConfigData.FULLSCREEN.equals("true")){
            LogUtils.info("Full screen");
            driver.manage().window().maximize();
        }
        return driver;
    }

    private WebDriver initMSEdge() {
        WebDriver driver;
        EdgeOptions options = new EdgeOptions();
        if (ConfigData.HEADLESS.equals("true")){
            LogUtils.info("Launching Edge (Headless)");
            options.addArguments("--headless");
        }else {
            LogUtils.info("Launching Microsoft Edge");
        }
        driver = new EdgeDriver(options);
        return driver;
    }
    private WebDriver initGGChrome(){
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        if (ConfigData.HEADLESS.equals("true")) {
            LogUtils.info("Launching Google Chrome (Headless)");
            options.addArguments("--headless");
        }else {
            LogUtils.info("Launching Google Chrome");
        }
        driver = new ChromeDriver(options);
//        if (ConfigData.FULLSCREEN.equals("true")) {
//            LogUtils.info("Full screen");
//            driver.manage().window().maximize();
//        }
        return driver;
    }
}
