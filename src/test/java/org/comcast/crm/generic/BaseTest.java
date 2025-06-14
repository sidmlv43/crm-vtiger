package org.comcast.crm.generic;


import com.aventstack.extentreports.Status;
import org.comcast.crm.generic.fileUtils.PropReader;
import org.comcast.crm.generic.objectRepo.AbstractPage;
import org.comcast.crm.generic.objectRepo.LoginPage;
import org.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import org.comcast.crm.generic.webDriverUtils.WebDriverUtils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;
    protected static WebDriverUtils webUtils = new WebDriverUtils();
    protected PropReader prop;
    // protected ExcelMiner miner;
    protected static final String basePath = System.getProperty("user.dir");
    public static WebDriver sDriver;


    @BeforeSuite
    public void configBS() {
        System.out.println("----Before Suite-----");
    }

    //    @Parameters({"browser"})
    @BeforeTest
//    public void configBT(String browser) throws IOException {
    public void configBT() throws IOException {
        System.out.println("----Before Test----");
//        webUtils = new WebDriverUtils();
//        prop = new PropReader(basePath + "/src/main/resources/config.properties");
//        String BROWSER = browser != null ? browser: prop.get("browser");
//        driver = webUtils.getBrowser(BROWSER);
//        webUtils.maximizeWindow(driver);
//        webUtils.waitForPageToLoad(driver);
//        driver.get(prop.get("url"));
    }

    //    @Parameters({"browser"})
    @BeforeClass(groups = {"smokeTest", "regressionTest"})
//    public void configBC(String browser) throws IOException {
    public void configBC() throws IOException {
        prop = new PropReader(basePath + "/src/main/resources/config.properties");
        String URL = System.getProperty("url") == null ? prop.get("url") : System.getProperty("url");
        String BROWSER = System.getProperty("browser") == null ? prop.get("browser") : System.getProperty("browser");

        driver = webUtils.getBrowser(BROWSER);
        sDriver = driver;
//        UtilityClassObject.getTest().log(Status.INFO, "Initializing Browser ---> " + BROWSER);
        UtilityClassObject.setDriver(driver);

        webUtils.maximizeWindow(driver);
        webUtils.waitForPageToLoad(driver);
//        UtilityClassObject.setDriver(driver);

        driver.get(URL);
    }

    @BeforeMethod(groups = {"smokeTest", "regressionTest"})
    public void configBeforeMethod() {
        String USERNAME = System.getProperty("username") == null ? prop.get("username") : System.getProperty("username");
        String PASSWORD = System.getProperty("password") == null ? prop.get("password") : System.getProperty("password");

//        UtilityClassObject.getTest().log(Status.INFO, "Logging in ---> CRM");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(USERNAME, PASSWORD);
    }

    @AfterMethod(groups = {"smokeTest", "regressionTest"})
    public void configAfterMethod() {
        AbstractPage abs = new AbstractPage(driver);
//        UtilityClassObject.getTest().log(Status.INFO, "Logging out ---> CRM");
        abs.signout();
    }

    @AfterClass(groups = {"smokeTest", "regressionTest"})
    public void configAC() {
        UtilityClassObject.getTest().log(Status.INFO, "Quitting the browser");
        driver.quit();
    }
    @AfterSuite(groups = {"smokeTest", "regressionTest"})
    public void configAS() {
        System.out.println("----After Suite----");
    }
}
