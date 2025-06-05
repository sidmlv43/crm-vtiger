package org.comcast.crm.generic.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrgPage extends AbstractPage {

    @FindBy(xpath = "//img[@title='Create Organization...']/..")
    private WebElement addOrgButton;

    @FindBy(name = "accountname")
    private WebElement orgNameInputField;

    @FindBy(css = ".dvHeaderText")
    private WebElement savedOrgHeaderName;

    @FindBy(id = "dtlview_Organization Name")
    private WebElement savedOrgName;

    @FindBy(name = "industry")
    private WebElement industryDropdown;

    @FindBy(xpath = "//span[@id='dtlview_Industry']/font")
    private WebElement savedIndustryName;

    @FindBy(id = "phone")
    private WebElement phoneInputField;

    @FindBy(id = "dtlview_Phone")
    private WebElement savedPhoneNumber;

    @FindBy(xpath = "//a[@title='Organizations']")
    private List<WebElement> savedOrganizations;


    public OrgPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void createOrganization(String orgName) {
        addOrgButton.click();
        orgNameInputField.sendKeys(orgName);
        saveButton.click();
    }

    public void createOrganization(String orgName, String industry) {
        addOrgButton.click();
        orgNameInputField.sendKeys(orgName);
        webUtils.select(industryDropdown, industry);
        saveButton.click();
    }

    public void createOrganization(String orgName, String industry, String phoneNumber) {
        addOrgButton.click();
        orgNameInputField.sendKeys(orgName);
        webUtils.select(industryDropdown, industry);
        phoneInputField.sendKeys(phoneNumber);
        saveButton.click();
    }

    public String getSavedOrgNameHeaderText() {
        return savedOrgHeaderName.getText();
    }


    public String getSavedIndustryName() {
        return savedIndustryName.getText();
    }

    public String getSavedPhoneNumber() {
        return savedPhoneNumber.getText();
    }

    public String getSavedOrgName() {
        return savedOrgName.getText();
    }
}
