import Enums.Browser;
import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static org.openqa.selenium.remote.BrowserType.OPERA;

public class TestBaseAcmeShop {

    Logger LOG = Logger.getLogger(TestBaseAcmeShop.class);

    protected WebDriver webdriver;

    @BeforeTest
    public void setup() {
        Browser browser = Browser.valueOf(System.getProperty("browser", Browser.CHROME.toString()));
        switch (browser) {
            case CHROME:
                Configuration.browser = CHROME;
                break;
            case FIREFOX:
                Configuration.browser = FIREFOX;
                break;
            default: Configuration.browser = CHROME;
        }

        Configuration.baseUrl = "https://litecart.stqa.ru/en/";
        Configuration.assertionMode = AssertionMode.SOFT;
        open(Configuration.baseUrl);
        Configuration.pageLoadTimeout = 20000;
        WebDriverRunner.getWebDriver().manage().window().maximize();
        webdriver = WebDriverRunner.getWebDriver();
    }

    @BeforeMethod
    public void mainPage(){
        LOG.debug("Web-site opening");
        open(Configuration.baseUrl);
        LOG.debug("Web-site is opened");
    }

    @AfterMethod
    public void pageTeardown(){
        LOG.debug("Web-site closing");
        closeWebDriver();
        LOG.debug("Web-site is closed");
    }

//    @AfterTest
//    public void teardown() {
//        LOG.debug("Web-site closing");
//        closeWebDriver();
//        LOG.debug("Web-site is closed");
//    }
}

