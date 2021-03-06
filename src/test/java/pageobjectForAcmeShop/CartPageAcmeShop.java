package pageobjectForAcmeShop;

import HelperForAcmeShop.Locators;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

public class CartPageAcmeShop {
    private By quantityInCartLocator = Locators.getLocator("CartPageAcmeShop.quantityInCart");
    private By quantityInGridLocator = Locators.getLocator("CartPageAcmeShop.quantityInGrid");
    private By updateInCartLocator = Locators.getLocator("CartPageAcmeShop.updateInCart");

    private final WebDriver driver;

    public CartPageAcmeShop(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    public void changeQuantityTo3() {
        Actions builder = new Actions(driver);
        builder.doubleClick($(quantityInCartLocator)).perform();
        $(quantityInCartLocator).sendKeys("{UP 3}");  // я попыталась количество загнать в то, что принимает
        // метод, напр. int i, а в sendKeys написать i, но так не выходит, так как там нужно не вводить количество, а кликать вверх
    }

    public void clickUpdateInCart(){
        $(updateInCartLocator).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(quantityInCartLocator)));
    }

    public void assertRightNumberInCart() {
        String quantityFact = $(quantityInCartLocator).getAttribute("value");
        assertEquals(quantityFact, "3");
    }

    public void assertRightNumberInGrid() throws InterruptedException {
        Thread.sleep(1000); //поставила временно, пока в какой момент сайт стал тормозить
        String quantityGrid  = $(quantityInGridLocator).getText();
        assertEquals(quantityGrid, "3");
    }
}
