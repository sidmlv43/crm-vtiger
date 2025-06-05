package org.comcast.crm.generic.objectRepo;

import com.aventstack.extentreports.Status;
import org.comcast.crm.generic.misc.Helper;
import org.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import org.comcast.crm.generic.webDriverUtils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Represents the Contacts Page of the application.
 * This Page Object encapsulates all the web elements and actions pertaining to the Contacts page functionality.
 * It follows the Page Object Model design pattern to separate the UI element and interaction from the test logic.
 *
 * <p>
 *     This class should not contain any test assertions. Assertions should be made in the test classes.
 * </p>
 *
 * @author [Siddharth Malviya]
 * @see org.comcast.crm.generic.objectRepo.AbstractPage
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.support.PageFactory
 */

public class ContactPage extends AbstractPage {

    /**
     * Locator for add new contact button.
     */
    @FindBy(xpath = "//img[@title='Create Contact...']/..")
    private WebElement addContact;

    /**
     * Locator for the salutation dropdown menu.
     */
    @FindBy(name = "salutationtype")
    private WebElement salutationDropDown;

    /**
     * Locator for the firstname input field
     */
    @FindBy(name = "firstname")
    private WebElement firstNameInputField;

    /**
     * Locator for the last name input field.
     */
    @FindBy(name = "lastname")
    private WebElement lastNameInputField;

    /**
     * Locator for the saved first name.
     */
    @FindBy(xpath = "//span[@id='dtlview_First Name']")
    private WebElement savedFirstName;

    /**
     * Locator for the saved salutation type.
     */
    @FindBy(xpath = "//span[@id='dtlview_First Name']/..")
    private WebElement savedSalutationType;

    /**
     * Locators for selecting organization button.
     */
    @FindBy(xpath = "//input[@name='account_id']/following-sibling::img")
    private WebElement addOrgNameButton;

    /**
     * Locator for the saved last name.
     */
    @FindBy(id = "dtlview_Last Name")
    private WebElement savedLastName;

    /**
     * Locator for the search input field for organization.
     */
    @FindBy(css = "input#search_txt")
    private WebElement orgNameSearchInputField;

    @FindBy(xpath = "//input[@name='account_id']/following-sibling::img")
    private WebElement selectOrganizationButton;

    @FindBy(id = "jscal_field_support_end_date")
    private WebElement supportEndDateInputField;

    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchOrgButton;

    @FindBy(xpath = "//td[@id='mouseArea_Organization Name']/a")
    private WebElement savedOrgName;

    @FindBy(id = "dtlview_Support End Date")
    private WebElement savedSupportEndDate;

    @FindBy(name = "search_text")
    private WebElement orgNameSearchTextInputField;

    @FindBy(id = "mouseArea_Organization Name")
    private WebElement savedOrgNameLink;


    public ContactPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // getters
    public String getActualLastName() {
        return savedLastName.getText();
    }

    public String getActualSupportEndDate() {
        return savedSupportEndDate.getText();
    }

    public String getActualSalutationType() {
        return savedSalutationType.getText();
    }

    public String getActualFirstName() {
        return savedFirstName.getText();
    }

    public String getActualSavedOrgName()
    {
        return savedOrgNameLink.getText();
    }


    // Helper methods
    public void createContact(String lastName) {
        addContact.click();
        lastNameInputField.sendKeys(lastName);
        saveButton.click();
    }

    public void createContact(String firstName, String lastName) {
        addContact.click();
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        saveButton.click();
    }


    public void createContact(String salutation, String firstName, String lastName) {

        addContact.click();
        webUtils.select(salutationDropDown, "Mr.");
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        saveButton.click();

    }

    public void createContact(String salutation, String firstName, String lastName, int numberOfDays) {

        addContact.click();
        webUtils.select(salutationDropDown, "Mr.");
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        supportEndDateInputField.clear();
        supportEndDateInputField.sendKeys(Helper.addDaysToDate(LocalDate.now(), numberOfDays).toString());
        saveButton.click();
    }


    public void createContact (String salutation, String firstName, String lastName, int numberOfDays, String orgName)
    {
        UtilityClassObject.getTest().log(Status.INFO, "Clicked on Add Contact Button");
        addContact.click();
        webUtils.select(salutationDropDown, "Mr.");
        UtilityClassObject.getTest().log(Status.INFO, "Selecting Salutation");
        firstNameInputField.sendKeys(firstName);
        UtilityClassObject.getTest().log(Status.INFO, "Entering First Name");
        lastNameInputField.sendKeys(lastName);
        UtilityClassObject.getTest().log(Status.INFO, "Entering Last Name");
        supportEndDateInputField.clear();
        UtilityClassObject.getTest().log(Status.INFO, "Entering Support End Date");
        supportEndDateInputField.sendKeys(Helper.addDaysToDate(LocalDate.now(), numberOfDays).toString());
        selectOrganizationButton.click();
        UtilityClassObject.getTest().log(Status.INFO, "clicked on plus icon to select the organization");
        webUtils.switchToBrowserTabOnURl(driver, "form=TasksEditView&form_submit=false&fromlink=&recordid=");
        UtilityClassObject.getTest().log(Status.INFO, "Switching to new window to select organization.");
        orgNameSearchTextInputField.sendKeys(orgName);
        UtilityClassObject.getTest().log(Status.INFO, "Searching organization");
        searchOrgButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("status"))));
        driver.findElement(By.xpath("//input[@name='idlist']/following-sibling::div//td[contains(., '" + orgName + "')]/a")).click();
        UtilityClassObject.getTest().log(Status.INFO, "Selecting the organization");
        UtilityClassObject.getTest().log(Status.INFO, "Switching back to the original window");
        webUtils.switchToBrowserTabOnTitle(driver, " Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM");
        UtilityClassObject.getTest().log(Status.INFO, "Saving the contact.");
        saveButton.click();
    }

}
