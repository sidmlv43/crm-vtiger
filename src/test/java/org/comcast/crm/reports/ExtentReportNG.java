package org.comcast.crm.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.comcast.crm.generic.misc.Helper;

// Assuming Helper class is in your project and has getCurrentTimeStamp() method
// import your.package.Helper;


/**
 * Initializes and configures an {@link ExtentReports} object for generating comprehensive test reports.
 * This method sets up the report's path, name, title, and theme, along with basic system information.
 * <p>
 * The generated report will be stored in the 'reports' directory within the project,
 * with a dynamic file name based on the current timestamp to ensure uniqueness.
 * </p>
 *
 * @author [Siddharth Malviya]
 * @return An initialized {@link ExtentReports} instance ready for use in test execution.
 * This object can be used to create test entries and log test steps.
 * @see ExtentReports
 * @see ExtentSparkReporter
 * @see com.aventstack.extentreports.reporter.configuration.Theme
 * @see Helper#getCurrentTimeStamp()
 */
public class ExtentReportNG {

    /**
     * prevents instantiation of this class.
     */
    private ExtentReportNG() {

    }

    public static ExtentReports getReporterObject() {
        // step 1. create reporter object by passing the directory where you want to store reports.
        String reportName = Helper.getCurrentTimeStamp();
        String path = System.getProperty("user.dir") + "/reports/report_" + reportName + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        // step 2. configure the reporter by setting the report name and the document title.
        reporter.config().setReportName("CRM Report");
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setTheme(Theme.DARK);

        // step 3. create ExtentReports object and attach the reporter to the ExtentReport object.
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // step 4. set system info (optional)
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("browser", "Chrome");

        // step 5. return the ExtentReport Object.
        return extent;
    }
}
