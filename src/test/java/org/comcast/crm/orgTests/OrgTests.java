package org.comcast.crm.orgTests;

import org.comcast.crm.generic.BaseTest;
import org.comcast.crm.generic.fileUtils.ExcelMiner;
import org.comcast.crm.generic.misc.Helper;
import org.comcast.crm.generic.objectRepo.OrgPage;
import org.comcast.crm.listners.RetryNG;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrgTests extends BaseTest {

    @Test(groups = "smokeTest", retryAnalyzer = RetryNG.class)
    public void createOrgTest() {
        OrgPage orgPage = new OrgPage(driver);
        orgPage.gotoOrgPage();
        String orgName = "MetaQ_" + Helper.getRandomNumber();
        orgPage.createOrganization(orgName);
        String actOrgHeaderText = orgPage.getSavedOrgNameHeaderText();
        String actOrgName = orgPage.getSavedOrgName();

        Assert.assertTrue(actOrgHeaderText.contains(orgName));
        Assert.assertEquals(actOrgName, orgName);
    }


    @Test(groups = "regressionTest", retryAnalyzer = RetryNG.class)
    public void createOrgTestWithIndustry() {
        OrgPage orgPage = new OrgPage(driver);
        orgPage.gotoOrgPage();
        String orgName = "MetaQuest" + Helper.getRandomNumber();
        String industry = "Technology";

        orgPage.createOrganization(orgName, industry);
        String actOrgHeaderText = orgPage.getSavedOrgNameHeaderText();
        String actIndustryName = orgPage.getSavedIndustryName();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actIndustryName, industry);
        softAssert.assertTrue(actOrgHeaderText.contains(orgName));
        softAssert.assertAll();

    }


    @Test(dataProvider = "orgDataSet", groups = "regressionTest", retryAnalyzer = RetryNG.class)
    public void createOrgTestWithIndustryAndPhone(String orgName, String industry, String phone) {
        OrgPage orgPage = new OrgPage(driver);
        orgPage.gotoOrgPage();
        orgPage.createOrganization(orgName + "_" + Helper.getRandomNumber(), industry, phone);
        String actOrgHeaderText = orgPage.getSavedOrgNameHeaderText();
        String actIndustryName = orgPage.getSavedIndustryName();
        String actPhoneNumber = orgPage.getSavedPhoneNumber();
        Assert.assertEquals(actIndustryName, industry);
        Assert.assertTrue(actOrgHeaderText.contains(orgName));
        Assert.assertEquals(actPhoneNumber, phone);
    }

    @DataProvider(name = "orgDataSet")
    public Object[][] getOrgData() {
        ExcelMiner miner = new ExcelMiner(basePath + "/src/test/resources/org_data.xlsx");
        miner.loadSheet("Test Data");
        Object[][] result = new Object[miner.getRowCount() - 1][3];
        for (int i = 1; i < miner.getRowCount(); i++) {
            for (int j = 0; j < 3; j++) {
                result[i - 1][j] = miner.getCellData(i, j);
            }
        }
        return result;
    }
}
