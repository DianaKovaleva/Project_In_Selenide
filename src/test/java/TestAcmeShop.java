import HelperForAcmeShop.ScreenshotListener;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjectForAcmeShop.CartPageAcmeShop;
import pageobjectForAcmeShop.DuckPageAcmeShop;
import pageobjectForAcmeShop.HomePageAcmeShop;
import pageobjectForAcmeShop.SubcategoryPageAcmeShop;
import java.util.ArrayList;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

@Listeners({ScreenshotListener.class})

@Epic("Automation courses")
@Feature("Web-site with ducks testing")
public class TestAcmeShop extends TestBaseAcmeShop {


    @Test
    @Description("Checking the right redirection at the Header's links")
    public void ducksShopHeaders() throws Exception {
        HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
        SoftAssert softAssert = new SoftAssert();

        LOG.info("Checking that we got to the right page");
        homePage.clickRubberDucksHeader();
        String rubberDucksHeaderTitle = homePage.getTitle();
        //$("").shouldHave(text(rubberDucksHeaderTitle)); //не понятно, как в селенид, мы же не просто проверяем наличие текста, а смотрим title
        softAssert.assertEquals(rubberDucksHeaderTitle, "Rubber Ducks | My Store1");

        homePage.clickDeliveryInformationHeader();
        //$("title").shouldHave(text("Delivery Information | My Store1")); //так тоже не получилось
        //$("title").shouldHave(attribute("text", "Delivery Information | My Store1")); //так тоже не получилось
        String deliveryInformationHeaderTitle = homePage.getTitle();
        softAssert.assertEquals(deliveryInformationHeaderTitle, "Delivery Information | My Store1");

        homePage.clickTermsConditions();
        String termsConditionsHeaderTitle = homePage.getTitle();
        softAssert.assertEquals(termsConditionsHeaderTitle, "Terms & Conditions | My Store1");

        homePage.clickHomeHeader();
        String homeHeaderTitle = homePage.getTitle();
        softAssert.assertEquals(homeHeaderTitle, "Online Store | My Store1");

        softAssert.assertAll();

    }

        @Test
        @Description("Testing the cart and quantity")
        public void addingToCart() throws Exception {
            HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
            CartPageAcmeShop cartPage = new CartPageAcmeShop(webdriver);
            DuckPageAcmeShop duckPage = new DuckPageAcmeShop(webdriver);

             // Кликаем на красную уточку:
        homePage.clickRedDuck();

            // Добавляем ее в корзину:
        duckPage.clickAddToCartButton();
        duckPage.waitDuckAppearsInCart();

            // Кликаем на корзину:
        duckPage.goToCart();

            //Меняем кол-во товара на 3
        cartPage.changeQuantityTo3();

            //Нажимаем на Update
        cartPage.clickUpdateInCart();

            //Проверяем, отобразилось ли в корзине правильное количество(3)
        LOG.info("Checking the right number in the cart");
        cartPage.assertRightNumberInCart();

            //Проверяем, отобразилось ли правильное количество(3) в таблице ниже
        LOG.info("Checking the right number in the grid");
        cartPage.assertRightNumberInGrid();

    }

        @Test
        @Description("Sorting")
        public void subcategorySorting () throws Exception {
        HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
        SubcategoryPageAcmeShop subcategoryPage = new SubcategoryPageAcmeShop(webdriver);

        homePage.clickRubberDucksHeader();
        homePage.clickSubcategory();

        //Сортировка по имени

        subcategoryPage.clickNameSortingButton();

        LOG.debug("Creation of the array with existing names and its consequence");
        ArrayList<String> allNames = new ArrayList<>();
        String greenDuck = subcategoryPage.getTextOfGreenDuckName();
        allNames.add(greenDuck);

        String yellowDuck = subcategoryPage.getTextOfYellowDuckName();
        allNames.add(yellowDuck);

//        String pinkDuck = subcategoryPage.getTextOfPinkDuckName();
//        allNames.add(pinkDuck);

        LOG.debug("Creation of the array with desired names and its consequence");
        ArrayList<String> forNamesComparison = new ArrayList<>();
        forNamesComparison.add("Green DucK");
        forNamesComparison.add("Yellow Duck");
        //forNamesComparison.add("Розовая уточка");

        LOG.info("Assertion of the right sorting on names");
        assertEquals(allNames, forNamesComparison);

        //Сортировка по цене
        subcategoryPage.clickPriceSortingButton();

        LOG.debug("Cutting the currency sign to get the double");
        String yellowPriceTruncated = subcategoryPage.getTextOfYellowDuckPrice();
        double yellowPriceDouble = Double.parseDouble(yellowPriceTruncated);

        String greenPriceTruncated = subcategoryPage.getTextOfGreenDuckPrice();
        double greenPriceDouble = Double.parseDouble(greenPriceTruncated);

        String pinkPriceTruncated = subcategoryPage.getTextOfPinkDuckPrice();
        double pinkPriceDouble = Double.parseDouble(pinkPriceTruncated);

        LOG.debug("Creation of the array with existing prices and its consequence");
        ArrayList<Double> allPrices = new ArrayList<>();
        allPrices.add(yellowPriceDouble);
        allPrices.add(greenPriceDouble);
        allPrices.add(pinkPriceDouble);

        String result;
        LOG.info("Comparison of prices");
        if (allPrices.get(0) < allPrices.get(1) && allPrices.get(1) < allPrices.get(2)) {
            result = "Sorted";
        } else {
            result = "Not Sorted";
        }

        assertEquals(result, "Sorted");
        }

        @Test
        @Description("Right labels checking")
        public void labelsChecking() throws Exception {
        HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
        SubcategoryPageAcmeShop subcategoryPage = new SubcategoryPageAcmeShop(webdriver);

        homePage.clickRubberDucksHeader();
        homePage.clickSubcategory();

        //$(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/green-duck-p-2\"] div[class*=\"sticker\"]").shouldHave(text("NEW")); //так тоже не получилось
        LOG.debug("Getting text of titles and comparison with the expected ones");
        String greenDuckLabelString = subcategoryPage.getTextOfGreenDuckLabel();
        assertEquals(greenDuckLabelString, "NEW");

        String pinkDuckLabelString = subcategoryPage.getTextOfPinkDuckLabel();
        assertEquals(pinkDuckLabelString, "NEW");

        String yellowDuckLabelString = subcategoryPage.getTextOfYellowDuckLabel();
        assertEquals(yellowDuckLabelString, "SALE");
    }

    @Test
    public void searching() throws Exception {
        HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
        homePage.search("yellow"); //пока просто так, ничего не оцениваем на странице по результатам
        homePage.search("red");
        homePage.search("acme");
        Assert.fail("For reports testing");

    }


  }

