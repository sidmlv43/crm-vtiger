package org.comcast.crm.generic.objectRepo;

import org.comcast.crm.generic.databaseUtil.DataBaseUtil;
import org.comcast.crm.generic.fileUtils.ExcelMiner;
import org.comcast.crm.generic.fileUtils.PropReader;
import org.comcast.crm.generic.webDriverUtils.WebDriverUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This page is an abstract page that contains references to the navbar
 * and other elements that is common to all the web pages.
 */
public class AbstractPage {

    protected WebDriver driver;

    protected WebDriverUtils webUtils = new WebDriverUtils();
    protected ExcelMiner miner;
    protected PropReader propReader;
    protected DataBaseUtil dbUtil;

    @FindBy(linkText = "Organizations")
    private WebElement organizations;

    @FindBy(linkText = "Contacts")
    private WebElement contacts;

    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    protected WebElement saveButton;

    @FindBy(css = "input.txtBox")
    protected WebElement searchBoxInputField;

    @FindBy(id = "bas_searchfield")
    protected WebElement searchInDropdown;

    @FindBy(name = "submit")
    protected WebElement searchButton;

    @FindBy(id = "status")
    protected WebElement statusBar;

    @FindAll({
            @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']/.."),
            @FindBy(xpath = "//img[@border=0 and contains(@src,'user.PNG')]/..")
    })
    private WebElement userIconImage;


    @FindAll({
            @FindBy(linkText = "Sign Out"),
            @FindBy(xpath = "//a[text()='Sign Out']")

    })

    private WebElement signoutButton;


    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void signout() {
        try {
            webUtils.moveMouseOnElement(driver, userIconImage);
            webUtils.waitTillElementAppears(driver, signoutButton);
            signoutButton.click();
        } catch (Exception e) {
            System.out.println("inside signout catch block");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", signoutButton);
        }

    }


    public OrgPage gotoOrgPage() {
        organizations.click();
        return new OrgPage(driver);
    }

    public ContactPage goToContactsPage() {
        contacts.click();
        return new ContactPage(driver);
    }
}
