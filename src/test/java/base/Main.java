package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ExtentReportManager;

public class Main {
    protected WebDriver driver;
    protected ExtentReports report;
    protected ExtentTest test;

    @BeforeEach
    public void setUp(TestInfo info) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        report = ExtentReportManager.create(info.getDisplayName().replace(" ", "_"));
        test = report.createTest(info.getDisplayName());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
        report.flush();
    }

}
