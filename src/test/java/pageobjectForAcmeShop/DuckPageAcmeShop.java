package pageobjectForAcmeShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;

public class DuckPageAcmeShop {
    private By addToCartLocator = By.name("add_cart_product");
    private By cartLocator = By.cssSelector("#cart");
    private By cartWithDuckLocator = By.cssSelector("img[src=\"/includes/templates/default.catalog/images/cart_filled.png\"]");

    private final WebDriver driver;


    public DuckPageAcmeShop(WebDriver driver) {
        this.driver = driver;
    } // как вообще реализуется селенид в случае с page objects и, в частности, с конструктором?

    public void clickAddToCartButton(){
        $(addToCartLocator).click();
    }

    public void goToCart(){
        $(cartLocator).click();
    }

    public void waitDuckAppearsInCart(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(cartWithDuckLocator));
    }
}
