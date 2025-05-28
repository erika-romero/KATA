package utils;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    public static void capture(WebDriver driver, ExtentTest test, String name) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = name + "_" + timestamp + ".png";
        String relativePath = "test-report/" + fileName;
        String fullPath = System.getProperty("user.dir") + "/" + relativePath;

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(fullPath));
            test.info("Captura de pantalla:").addScreenCaptureFromPath(fileName);
        } catch (IOException e) {
            test.warning("No se pudo guardar la captura de pantalla: " + e.getMessage());
        }
    }
}