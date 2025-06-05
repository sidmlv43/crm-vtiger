package org.comcast.crm.listners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.comcast.crm.generic.BaseTest;
import org.comcast.crm.generic.misc.Helper;
import org.comcast.crm.generic.webDriverUtils.UtilityClassObject;
import org.comcast.crm.reports.ExtentReportNG;
import org.testng.*;


public class Listeners extends BaseTest implements ITestListener, ISuiteListener {

    public static ExtentReports reports;
    public ExtentTest test;

    @Override
    public void onStart(ISuite suite) {
//        ISuiteListener.super.onStart(suite);

        reports = ExtentReportNG.getReporterObject();
    }

    @Override
    public void onFinish(ISuite suite) {
        reports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
//        ITestListener.super.onTestStart(result);
        test = reports.createTest(result.getMethod().getMethodName());
        UtilityClassObject.setTest(test);
        test.log(Status.INFO, result.getMethod().getMethodName() + "--> STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS, result.getMethod().getMethodName() + "--> COMPLETED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        System.out.println("Will take screenshot");
        test.log(Status.FAIL, result.getMethod().getMethodName() + "--> FAILED");

        String testName = result.getMethod().getMethodName();
        String timeStamp = Helper.getCurrentTimeStamp();
        String screenShot = webUtils.takeScreenShot(sDriver);
        test.addScreenCaptureFromBase64String(screenShot, testName + "_" + timeStamp);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

}
