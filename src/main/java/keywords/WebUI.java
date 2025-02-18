package keywords;

import com.aventstack.extentreports.Status;
import constants.ConfigData;
import drivers.DriverManager;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import reports.AllureManager;
import reports.ExtentTestManager;
import utils.LogUtils;

import java.time.Duration;
import java.util.List;

import static java.lang.System.out;

public class WebUI {

    private static JavascriptExecutor js = (JavascriptExecutor) getDriver();
    private static SoftAssert softAssert = new SoftAssert();
    private static WebDriverWait wait;

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static WebElement getWebElement(By by) {
        return getDriver().findElement(by);
    }

    public static List<WebElement> getListWebElement(By by){
        return getDriver().findElements(by);
    }

    //NAVIGATION

    @Step("Open URL: {0}")
    public static void openURL(String URL) {
        getDriver().get(URL);
        waitForPageLoad(2);
        LogUtils.info("Open URL: " + URL);
        reportInfo("Open URL: " + URL);
    }

    public static String getURL() {
        LogUtils.info(getDriver().getCurrentUrl());
        reportInfo(getDriver().getCurrentUrl());
        return getDriver().getCurrentUrl();
    }

    public static String getTitle() {
        LogUtils.info(getDriver().getTitle());
        reportInfo(getDriver().getTitle());
        return getDriver().getTitle();
    }

    //ELEMENT INTERACTION
    @Step("Click element: {0}")
    public static void clickElement(By by) {
        waitForPageLoad();
        if (verifyClickable(by)) {
            js.executeScript("arguments[0].click();", getWebElement(by));
            LogUtils.info("Click element with JavaScript: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Click Element with JavaScript: " + by.toString());
            AllureManager.saveTextLog("Click Element with JavaScript: " + by.toString());
        } else {
            getWebElement(by).click();
            LogUtils.info("Click Element: " + by.toString());
            ExtentTestManager.logMessage(Status.INFO, "Click Element: " + by.toString());
            AllureManager.saveTextLog("Click Element: " + by.toString());
        }
    }

    public static void actionClick(By by) {
        Actions actions = new Actions(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT));
        actions.moveToElement(getWebElement(by)).click().build().perform();
    }

    @Step("Input: (1) - To element: (0)")
    public static void setText(By by, String text) {
        getWebElement(by).sendKeys(text);
        LogUtils.info("Input: " + text + " - To element: " + by);
        reportInfo("Input: " + text + " - To element: " + by);
    }

    @Step("Get text of element {0}")
    public static String getText(By by) {
        waitForVisible(by);
        LogUtils.info("Text of element (" + by + ") is: " + getWebElement(by).getText());
        reportInfo("Text of element (" + by + ") is: " + getWebElement(by).getText());
        return getWebElement(by).getText();
    }

    //VERIFY ELEMENT=
    @Step("Verify element -{0}- visible")
    public static boolean verifyVisible(By by) {
        LogUtils.info("Verify element visible: " + by);
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.SLEEP_TIME));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info(by + " -> Element is Visible");
            reportInfo(by + " -> Element is Visible");
            return true;
        } catch (Exception e) {
            reportWarning(by + " -> Element is Not Visible");
            LogUtils.error(by + " -> Element is Not Visible");
            Assert.fail(by + " -> Element is Not Visible");
            return false;
        }
    }
    @Step("Verify element -{0}- visible")
    public static boolean verifyVisible(By by, String message) {
        LogUtils.info("Verify element visible: " + by);
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.SLEEP_TIME));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info(by + " -> Element is Visible");
            reportInfo(by + " -> Element is Visible");
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                reportWarning(by + " -> Element is Not Visible");
                LogUtils.error(by + " -> Element is Not Visible");
                Assert.fail(by + " -> Element is Not Visible");
            } else {
                reportWarning(message);
                LogUtils.error(message);
                Assert.fail(message);
            }
            return false;
        }
    }
    @Step("Verify element -{0}- visible")
    public static boolean verifyVisible(By by, String message, int timeout) {
        LogUtils.info("Verify element visible: " + by);
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.SLEEP_TIME));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info(by + " -> Element is Visible");
            reportInfo(by + " -> Element is Visible");
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                reportWarning(by + " -> Element is Not Visible");
                LogUtils.error(by + " -> Element is Not Visible");
                Assert.fail(by + " -> Element is Not Visible");
            } else {
                reportWarning(message);
                LogUtils.error(message);
                Assert.fail(message);
            }
            return false;
        }
    }

    @Step("Verify {0} EQUAL {1}")
    public static boolean verifyEqual(Object actual, Object expect) {
        boolean check = actual.equals(expect);
        if (check) {
            LogUtils.info("Verify Equals: " + actual + " = " + expect);
            reportInfo("Verify Equals: " + actual + " = " + expect);
        } else {
            LogUtils.error("Verify Equals: " + actual + " != " + expect);
            reportWarning("Verify Equals: " + actual + " != " + expect);
            Assert.assertEquals(actual, expect, "Verify Equals: " + actual + " != " + expect);
        }
        return check;
    }
    @Step("Verify {0} EQUAL {1}")
    public static boolean verifyEqual(Object actual, Object expect, String message) {
        boolean check = actual.equals(expect);
        if (check) {
            LogUtils.info("Verify Equals: " + actual + " = " + expect);
            reportInfo("Verify Equals: " + actual + " = " + expect);
        } else {
            LogUtils.error("Verify Equals: " + actual + " != " + expect);
            reportWarning("Verify Equals: " + actual + " != " + expect);
            Assert.assertEquals(actual, expect, message);
        }
        return check;
    }

    @Step("Verify TRUE: {0}")
    public static boolean verifyTrue(boolean condition) {
        if (condition) {
            LogUtils.info("Verify True of condition: " + condition);
            reportInfo("Verify True of condition: " + condition);
        } else {
            LogUtils.error("Condition: (" + condition + ") in Not True");
            reportWarning("Condition: (" + condition + ") in Not True");
            Assert.assertTrue(condition);
        }
        return condition;
    }
    @Step("Verify TRUE: {0}")
    public static boolean verifyTrue(boolean condition, String message) {
        if (condition) {
            LogUtils.info("Verify True of condition: " + condition);
            reportInfo("Verify True of condition: " + condition);
        } else {
            LogUtils.error("Condition: (" + condition + ") in Not True");
            reportWarning("Condition: (" + condition + ") in Not True");
            Assert.assertTrue(condition, message);
        }
        return condition;
    }

    @Step("Verify CONTAIN {0} AND {1}")
    public static boolean verifyContain(String object, String contain) {
        boolean check = object.contains(contain);
        if (check) {
            LogUtils.info(object + " -Contain- " + contain);
            reportInfo(object + " -Contain- " + contain);
        } else {
            LogUtils.error(object + " -Not Contain- " + contain);
            reportWarning(object + " -Not Contain- " + contain);
            Assert.assertTrue(check, object + " -Not Contain- " + contain);
        }
        return check;
    }
    @Step("Verify CONTAIN {0} AND {1}")
    public static boolean verifyContain(String object, String contain, String message) {
        boolean check = object.contains(contain);
        if (check) {
            LogUtils.info(object + " -Contain- " + contain);
            reportInfo(object + " -Contain- " + contain);
        } else {
            LogUtils.error(object + " -Not Contain- " + contain);
            reportWarning(object + " -Not Contain- " + contain);
            Assert.assertTrue(check, message);
        }
        return check;
    }

    @Step("Verify Clickable of element: {0}")
    public static boolean verifyClickable(By by){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.SLEEP_TIME));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info(by + " --> Can Clickable");
            reportInfo(by + " --> Can Clickable");
            return true;
        } catch (Exception e) {
            LogUtils.error(by + " --> CANNOT Clickable in " + ConfigData.TIMEOUT + "(s) -" + e.getMessage());
            reportWarning(by + " --> CANNOT Clickable in " + ConfigData.TIMEOUT + "(s) -" + e.getMessage());
            Assert.fail(by + " --> CANNOT Clickable in " + ConfigData.TIMEOUT + "(s) -" + e.getMessage());
            return false;
        }
    }
    @Step("Verify Clickable of element: {0}")
    public static boolean verifyClickable(By by, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.SLEEP_TIME));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info(by + " --> Can Clickable");
            reportInfo(by + " --> Can Clickable");
            return true;
        } catch (Exception e) {
            LogUtils.error(by + " --> CANNOT Clickable in " + timeout + "(s) -" + e.getMessage());
            reportWarning(by + " --> CANNOT Clickable in " + timeout + "(s) -" + e.getMessage());
            Assert.fail(by + " --> CANNOT Clickable in " + timeout  + "(s) -" + e.getMessage());
            return false;
        }
    }
    @Step("Verify Clickable of element: {0}")
    public static boolean verifyClickable(By by, String message){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.SLEEP_TIME));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info(by + " --> Can Clickable");
            reportInfo(by + " --> Can Clickable");
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error(by + " --> CANNOT Clickable in " + ConfigData.TIMEOUT + "(s) -" + e.getMessage());
            reportWarning(by + " --> CANNOT Clickable in " + ConfigData.TIMEOUT + "(s) -" + e.getMessage());
                Assert.fail(by + " --> CANNOT Clickable in " + ConfigData.TIMEOUT + "(s) -" + e.getMessage());
            } else {
                LogUtils.error(message + " :" + by);
                Assert.fail(message + " :" + by);
            }
            return false;
        }
    }

    //CUSTOM VERIFY
    public static void verifyFromError(String text) {
        for (int i : new int[]{1, 2, 3, 4, 5, 6, 8, 10, 11, 12}) {
            //Check element message is exist
            if (getListWebElement(By.xpath("//tr[" + i + "]//td[@width='50%']//span")).size()<1) {
                LogUtils.info(getWebElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText() + " Is Correct");
                reportInfo(getWebElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText() + " Is Correct");
            } else {
                String error = getWebElement(By.xpath("//tr[" + i + "]//td[@width='50%']//span")).getText();
                boolean check = error.contains(text);
                //Check fields REQUIRED
                if (check) {
                    LogUtils.warn(error);
                    reportWarning(error);
                } else {
                    LogUtils.error("This message: " + getWebElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText());
                    reportWarning("This message: " + getWebElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText());
                }
            }
        }
    }

    public static void clickActions(By by){
        Actions actions = new Actions(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT));
        actions.moveToElement(getWebElement(by)).click();

    }

    //WAIT
    public static void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.LOAD_TIME), Duration.ofMillis(ConfigData.SLEEP_TIME));
        ExpectedCondition<Boolean> jsLoad =
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
        if (!jsReady) {
            LogUtils.info("Javascript is Not Ready");
            try {
                wait.until(jsLoad);
            } catch (Throwable e) {
                e.printStackTrace();
                Assert.fail("Timeout (" + ConfigData.LOAD_TIME + ") waiting for page load");
            }
        }
    }
    public static void waitForPageLoad(int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.SLEEP_TIME));
        ExpectedCondition<Boolean> jsLoad =
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
        if (!jsReady) {
            LogUtils.info("Javascript is Not Ready");
            try {
                wait.until(jsLoad);
            } catch (Throwable e) {
                e.printStackTrace();
                Assert.fail("Timeout (" + timeout + ") waiting for page load");
            }
        }
    }

    public static WebElement waitForVisible(By by) {
        waitForPageLoad();
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT));
            boolean check = verifyVisible(by);
            if (check == false) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable e) {
            LogUtils.error("");
            Assert.fail("");
        }
        return null;
    }
    public static WebElement waitForVisible(By by, int timeout) {
        waitForPageLoad();
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(timeout));
            boolean check = verifyVisible(by);
            if (check) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable e) {
            LogUtils.error("");
            Assert.fail("");
        }
        return null;
    }

    public static void sleep(double time) {
        try {
            Thread.sleep((long) time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //UTILITY
    private static void reportInfo(String message) {
        ExtentTestManager.logMessage(Status.INFO, message);
        AllureManager.saveTextLog(message);
    }
    private static void reportWarning(String message) {
        ExtentTestManager.logMessage(Status.WARNING, message);
        AllureManager.saveTextLog(message);
    }
    private static void reportFail(String message) {
        ExtentTestManager.logMessage(Status.FAIL, message);
        AllureManager.saveTextLog(message);
    }

    @Step("Choose element {0}, {1} option {2}")
    public static void chooseOptions(By by, String type, String options){
        Select select = new Select(getWebElement(by));
        switch (type.trim().toLowerCase()) {
            case "value" -> {
                select.selectByValue(options);
            }
            case "index" -> {
                select.selectByIndex(Integer.parseInt(options));
            }
            case "text" -> {
                select.selectByVisibleText(options);
            }
        }
        LogUtils.info("Select (" + by + ") " + type + ": " + options);
        reportInfo("Select (" + by + ") " + type + ": " + options);
    }
}
