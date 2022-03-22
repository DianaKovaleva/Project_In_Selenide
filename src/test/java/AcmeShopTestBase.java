import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class AcmeShopTestBase {

    protected WebDriver webdriver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        Configuration.baseUrl = "https://litecart.stqa.ru/en/";
        Configuration.browser = CHROME;
        Configuration.assertionMode = AssertionMode.SOFT;
        open(Configuration.baseUrl);
        Configuration.pageLoadTimeout = 5000;
        webdriver = WebDriverRunner.getWebDriver();
    }

    @BeforeMethod
    public void mainPage(){
        open(Configuration.baseUrl);
    }

    @AfterTest
    public void teardown() {
        closeWebDriver();
    }
}

