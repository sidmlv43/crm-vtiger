package org.comcast.crm.generic.webDriverUtils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;


public class UtilityClassObject {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest test) {
        UtilityClassObject.test.set(test);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        UtilityClassObject.driver.set(driver);
    }
}
