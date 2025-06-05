package org.comcast.crm.contactTests;

import com.aventstack.extentreports.Status;
import org.comcast.crm.generic.BaseTest;
import org.comcast.crm.generic.misc.Helper;
import org.comcast.crm.generic.objectRepo.AbstractPage;
import org.comcast.crm.generic.objectRepo.ContactPage;
import org.comcast.crm.generic.objectRepo.OrgPage;
import org.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import org.comcast.crm.listners.RetryNG;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;

public class ContactTests extends BaseTest {


    @Test(groups = "smokeTest", retryAnalyzer = RetryNG.class)
    public void createContactTest() {
        UtilityClassObject.getTest().log(Status.INFO, "navigating to ---> Contacts page");
        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactsPage();
        String lastName = "Shukla";
        UtilityClassObject.getTest().log(Status.INFO, "creating contact with lastName");
        contactPage.createContact(lastName);
        String actLastName = contactPage.getActualLastName();
        UtilityClassObject.getTest().log(Status.INFO, "verifying lastName");
        Assert.assertEquals(actLastName, lastName);
    }


    @Test(groups = "regressionTest", retryAnalyzer = RetryNG.class)
    public void createContactWithFirstNameTest() {
        ContactPage contactPage = new ContactPage(driver);
        UtilityClassObject.getTest().log(Status.INFO, "navigating to ---> Contacts page");
        contactPage.goToContactsPage();
        String firstName = "Nitin";
        String lastName = "Shukla";
        UtilityClassObject.getTest().log(Status.INFO, "creating contact with firstName and lastName");

        contactPage.createContact(firstName, lastName);

        String actLastName = contactPage.getActualLastName();
        String actFirstName = contactPage.getActualFirstName();
        UtilityClassObject.getTest().log(Status.INFO, "verifying firstName and lastName");

        Assert.assertEquals(actFirstName, firstName);
        Assert.assertEquals(actLastName, lastName);


    }

    @Test(groups = "regressionTest", retryAnalyzer = RetryNG.class)
    public void createContactWithSalutationTest() {
        ContactPage contactPage = new ContactPage(driver);
        UtilityClassObject.getTest().log(Status.INFO, "navigating to ---> Contacts page");
        contactPage.goToContactsPage();
        String firstName = "Jatin";
        String lastName = "Shukla";
        String salutation = "Mr.";
        UtilityClassObject.getTest().log(Status.INFO, "creating contact with salutation, firstName and lastName");

        contactPage.createContact(salutation, firstName, lastName);


        String actLastName = contactPage.getActualLastName();
        String actFirstName = contactPage.getActualFirstName() + " ";
        String actSalutation = contactPage.getActualSalutationType();


        UtilityClassObject.getTest().log(Status.INFO, "verifying salutation, firstName and lastName");

        Assert.assertEquals(actFirstName, firstName);
        Assert.assertEquals(actLastName, lastName);
        Assert.assertTrue(actSalutation.contains(salutation));
    }


    @Test(retryAnalyzer = RetryNG.class)
    public void createContactWithSupportEndDate() {
        UtilityClassObject.getTest().log(Status.INFO, "Navigating to ---> Contacts Page");
        ContactPage contactPage = new ContactPage(driver);

        contactPage.goToContactsPage();
        String firstName = "Sunil";
        String lastName = "Dubey";
        String salutation = "Mr.";

        LocalDate date = Helper.addDaysToDate(LocalDate.now(), 60);
        UtilityClassObject.getTest().log(Status.INFO, "creating contact with salutation, firstName, lastName and support end data.");
        contactPage.createContact(salutation, firstName, lastName, 60);


        String actLastName = contactPage.getActualLastName();
        String actFirstName = contactPage.getActualFirstName();
        String actSalutation = contactPage.getActualSalutationType();
        String actSupportEndDate = contactPage.getActualSupportEndDate();

        UtilityClassObject.getTest().log(Status.INFO, "Verifying salutation, firstName, lastName and support end date");
        Assert.assertEquals(actFirstName, firstName);
        Assert.assertEquals(actLastName, lastName);
        Assert.assertTrue(actSalutation.contains(salutation));
        Assert.assertEquals(actSupportEndDate, date.toString());
    }


    @Test
    public void createContactWithOrg() {
        AbstractPage abstractPage = new AbstractPage(driver);

        UtilityClassObject.getTest().log(Status.INFO, "Navigating to organizations page");
        OrgPage orgPage = abstractPage.gotoOrgPage();

        orgPage.gotoOrgPage();
        String orgName = "amazon_" + Helper.getRandomNumber();
        UtilityClassObject.getTest().log(Status.INFO, "creating an organization");
        orgPage.createOrganization(orgName);

        String actOrgName = orgPage.getSavedOrgName();
        String actOrgHeaderText = orgPage.getSavedOrgNameHeaderText();

        ContactPage contactPage = abstractPage.goToContactsPage();

        String firstName = "Siddharth";
        String lastName = "Malviya";

        String salutation = "Mr.";
        int supportTill = 30;
        contactPage.createContact(salutation, firstName, lastName, supportTill, orgName);

        UtilityClassObject.getTest().log(Status.INFO, "Contact created.");
        String actFirstName = contactPage.getActualFirstName();
        String actLastName = contactPage.getActualLastName();
        String actSalutation = contactPage.getActualSalutationType();
        SoftAssert softAssert = new SoftAssert();

        UtilityClassObject.getTest().log(Status.INFO, "Checking equality of Organization name");
        softAssert.assertEquals(actOrgName, orgName);

        UtilityClassObject.getTest().log(Status.INFO, "Checking if Saved organization header text contains the actual organization name.");
        softAssert.assertTrue(actOrgHeaderText.contains(actOrgName));

        UtilityClassObject.getTest().log(Status.INFO, "Checking equality of first name");
        softAssert.assertEquals(actFirstName, firstName);

        UtilityClassObject.getTest().log(Status.INFO, "Checking equality of last name");
        softAssert.assertEquals(actLastName, lastName);

        UtilityClassObject.getTest().log(Status.INFO, "Checking equality of salutation type");
        softAssert.assertEquals(salutation, actSalutation);

        softAssert.assertAll();
    }

}
