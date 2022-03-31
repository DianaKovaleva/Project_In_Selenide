import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBaseAcmeShop {

    Logger LOG = Logger.getLogger(TestBaseAcmeShop.class);

    protected WebDriver webdriver;

    @BeforeTest
    public void setup() {
        Configuration.baseUrl = "https://litecart.stqa.ru/en/";
        Configuration.browser = CHROME;
        Configuration.assertionMode = AssertionMode.SOFT;
        open(Configuration.baseUrl);
        Configuration.pageLoadTimeout = 5000;
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

