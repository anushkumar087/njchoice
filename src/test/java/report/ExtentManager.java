package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/extent-report.html");
        reporter.config().setReportName("NJ Choice Automation");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Anush Malli");
        return extentReports;
    }
}
