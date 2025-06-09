package org.comcast.crm.generic.webDriverUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;


/**
 * A utility class that provides common browser-related helper methods
 * using Selenium WebDriver.
 * <p>
 * Includes methods to initialize browser instances, manage windows,
 * perform user interactions, handle dropdowns, frame switching,
 * waiting strategies, and screenshot capturing.
 * </p>
 *
 * <p><b>Dependencies:</b> Requires Selenium WebDriver API.</p>
 *
 * <p><b>Note:</b> Make sure to set system properties or WebDriverManager
 * for respective drivers before invoking {@code getBrowser()}.</p>
 *
 * @author [Siddharth Malviya]
 * @version 1.0
 */

public class WebDriverUtils {

    /**
     * Returns a WebDriver instance based on the provided browser name.
     *
     * @param browser the name of the browser (e.g., "chrome", "firefox", "edge")
     * @return a WebDriver instance
     */
    public WebDriver getBrowser(String browser) {
        switch (browser) {
            case "firefox":
                return new FirefoxDriver();

            case "edge":
                return new EdgeDriver();

            case "safari":

                return new SafariDriver();

            default:
                return new ChromeDriver();
        }
    }



    /**
     * Maximizes the browser window.
     *
     * @param driver the WebDriver instance
     */
    public void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }


    /**
     * Performs a double click on the specified element.
     *
     * @param driver the WebDriver instance
     * @param el     the WebElement to double-click
     */
    public void doubleClick(WebDriver driver, WebElement el) {
        Actions act = new Actions(driver);

        act.doubleClick(el).perform();
    }

    /**
     * Moves the mouse pointer to the specified element.
     *
     * @param driver the WebDriver instance
     * @param el     the WebElement to hover over
     */
    public void moveMouseOnElement(WebDriver driver, WebElement el) {
        Actions act = new Actions(driver);

        act.moveToElement(el).perform();
    }

    /**
     * Selects an option in a dropdown using its index.
     *
     * @param element the select element
     * @param index   the index of the option to select
     */
    public void select(WebElement element, int index) {
        Select sel = new Select(element);
        sel.selectByIndex(index);
    }


    /**
     * Selects an option in dropdown using visible text
     * @param element - the select element
     * @param txt - the visible text of the option
     */
    public void select(WebElement element, String txt) {
        Select sel = new Select(element);
        sel.selectByVisibleText(txt);
    }


    public void switchToFrame(WebDriver driver, WebElement el) {
        driver.switchTo().frame(el);
    }


    /**
     *  Switches the webdriver context to a frame using a name or Id
     * @param driver the Web Driver instance
     * @param nameId the name of the id of the Web Element.
     */
    public void switchToFrame(WebDriver driver, String nameId) {
        driver.switchTo().frame(nameId);
    }

    /**
     * Switches to a browser tab whose title contains the specified partial title.
     *
     * @param driver        the WebDriver instance
     * @param partialTitle  partial string to match in the tab title
     */
    public void switchToBrowserTabOnTitle(WebDriver driver, String partialTitle) {
        Set<String> wIds = driver.getWindowHandles();
        Iterator<String> it = wIds.iterator();

        while (it.hasNext()) {
            String wId = it.next();
            driver.switchTo().window(wId);

            if (driver.getTitle().contains(partialTitle)) break;
        }
    }

    /**
     * Switches to a browser tab whose title contains the specified partial title.
     *
     * @param driver        the WebDriver instance
     * @param partialURL  partial string to match in the tab URL
     */
    public void switchToBrowserTabOnURl(WebDriver driver, String partialURL) {
        Set<String> wIds = driver.getWindowHandles();

        for (String wId : wIds) {
            driver.switchTo().window(wId);

            if (Objects.requireNonNull(driver.getCurrentUrl()).contains(partialURL)) break;
        }
    }

    /**
     * Waits up to 20 seconds until the specified element becomes visible.
     *
     * @param driver  the WebDriver instance
     * @param element the element to wait for
     */
    public void waitTillElementAppears(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the presence of the specified element on the page.
     *
     * @param driver the WebDriver instance
     * @param el     the element to wait for
     */
    public void waitForElementPresence(WebDriver driver, WebElement el) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOf(el));
    }


    /**
     * Waits up to 20 seconds for the page to load completely using an implicit wait.
     *
     * @param driver the WebDriver instance
     */
    public void waitForPageToLoad(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    /**
     * Takes a screenshot and saves it to the local "reports/screenshots" directory
     * with the specified filename.
     *
     * @param driver   the WebDriver instance
     * @param fileName the name of the screenshot file (without extension)
     * @throws IOException if the screenshot cannot be saved
     */
    public void takeScreenShot(WebDriver driver, String fileName) throws IOException {
        TakesScreenshot tks = (TakesScreenshot) driver;
        File source = tks.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "/reports/screenshots/" + fileName + ".jpg");
        FileHandler.copy(source, dest);
    }


    /**
     * Takes a screenshot and returns it as a Base64-encoded string.
     *
     * @param driver the WebDriver instance
     * @return Base64 string representation of the screenshot
     */
    public String takeScreenShot(WebDriver driver) {
        TakesScreenshot tks = (TakesScreenshot) driver;
        return tks.getScreenshotAs(OutputType.BASE64);
    }
}
