import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

//для домашки ДЗ 28. Задание с использованием метода isDisplayed
public class TestForRozetka {

    WebDriver driver;

    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\IdeaProjects\\FirstProjectForAutomation\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://rozetka.com.ua/");
    }

    @Test
    public void testRozetkaTitle() throws InterruptedException, IOException {
        WebElement searchInput = driver.findElement(By.xpath("//input[@name='search']"));
        searchInput.sendKeys("Mac");
        WebElement searchBtn = driver.findElement(By.xpath("//button[contains(text(), 'Найти')]"));
        searchBtn.click();

        WebElement firstProduct = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='goods-title__heading']")));
        String firstProductTitleText = firstProduct.getText();
        firstProduct.click();

        WebElement productPageTitle = driver.findElement(By.xpath("//h1[@class='product__title']"));
        String productPageTitleText = productPageTitle.getAttribute("innerText");
        Assert.assertEquals(firstProductTitleText, productPageTitleText, "Title doesn't equals");

        WebElement firstActiveLink = driver.findElement(By.xpath("//a[@class='tabs__link tabs_link--active']"));
        String firstActiveLinkColor = firstActiveLink.getCssValue("color");
        Assert.assertEquals(firstActiveLinkColor, "rgba(0, 160, 70, 1)", "Color of first active tab is not green");

        WebElement availabilityOfProduct = driver.findElement(By.xpath("//p[@class='product__status product__status_color_green']"));
        String availabilityOfProductColor = availabilityOfProduct.getCssValue("color");

        String productPrice = driver.findElement(By.xpath("//p[@class='product-price__big product-prices__big_color_red']")).getText();

        if (availabilityOfProduct.isDisplayed() && availabilityOfProductColor.equals("rgba(0, 160, 70, 1)")){
            FileWriter fileWriter = new FileWriter("text.txt");
            fileWriter.write(productPageTitleText + " - " + productPrice);
            fileWriter.close();
        }
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }
}
