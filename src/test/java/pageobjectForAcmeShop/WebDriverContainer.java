package pageobjectForAcmeShop;

import Enums.Browser;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.BrowserType.*;

//!!!пока не используем этот класс
public class WebDriverContainer {

    private static WebDriver webdriver;

    public static void initDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        if (webdriver == null) {
            Browser browser = Browser.valueOf(System.getProperty("browser", Browser.CHROME.toString()));
            switch (browser) {
                case CHROME:
                    caps.setBrowserName(CHROME);
                    break;
                case FIREFOX:
                    caps.setBrowserName(FIREFOX);
                    break;
                case OPERA:
                    caps.setBrowserName(OPERA);
                    break;
                default:
                    caps.setBrowserName(CHROME);
            }
            WebDriver webdriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
            WebDriverRunner.setWebDriver(webdriver);
        }
    }
//        public static WebDriver getDriver() {
//            return webdriver;
//        }
//
//        public static void closeDriver(){
//        webdriver.quit();
//        }
    }

