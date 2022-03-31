package HelperForAcmeShop;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;

public class ScreenshotListener implements ITestListener {

//    @Override
//    public void onTestFailure(ITestResult result){
//        File screenshot = ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
//        File destFile = new File("C:\\Users\\screenshot.png");
//        try {
//            FileUtils.copyFile(screenshot, destFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onTestFailure(ITestResult result){
        try {
            Allure.addAttachment("Screenshot", getScreenshotAsInputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Allure.addAttachment("Source", "text", getPageSource(), ".html");
    }

    private static InputStream getScreenshotAsInputStream() throws FileNotFoundException {
        File screenshot = ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
        return new FileInputStream(screenshot);
    }

    private static String getPageSource() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }
}
