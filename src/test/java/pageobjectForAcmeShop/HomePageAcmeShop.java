package pageobjectForAcmeShop;
import HelperForAcmeShop.Locators;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static org.openqa.selenium.Keys.ENTER;

public class HomePageAcmeShop {
    private By homeHeaderLocator = Locators.getLocator("HomePageAcmeShop.homeHeader");
    private By rubberDucksHeaderLocator = Locators.getLocator("HomePageAcmeShop.rubberDucksHeader");
    private By deliveryInformationHeaderLocator = Locators.getLocator("HomePageAcmeShop.deliveryInformationHeader");
    private By termsConditionsHeaderLocator = Locators.getLocator("HomePageAcmeShop.termsConditionsHeader");
    private By redDuckLocator = Locators.getLocator("HomePageAcmeShop.redDuck");
    private By subcategoryLocator = Locators.getLocator("HomePageAcmeShop.subcategory");
    private By queryLocator = Locators.getLocator("HomePageAcmeShop.query");

    private final WebDriver driver;

    public HomePageAcmeShop(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    public void search(String searchInfo){
       $(queryLocator).sendKeys(searchInfo);
       $(queryLocator).sendKeys(Keys.chord(ENTER));
    }

    public void clickHomeHeader(){
        $(homeHeaderLocator).click();
    }

    public void clickRubberDucksHeader(){
        $(rubberDucksHeaderLocator).click();
    }

    public void clickDeliveryInformationHeader(){
        $(deliveryInformationHeaderLocator).click();
    }

    public void clickTermsConditions(){
        $(termsConditionsHeaderLocator).click();
    }

    public void clickSubcategory(){
        $(subcategoryLocator).click();
    }

    public void clickRedDuck(){
        $(redDuckLocator).click();
    }

    public String getTitle(){
        return title();
    }




}
