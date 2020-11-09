import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
        WebElement searchInput = driver.findElement(By.xpath("//input[@name='search]"));
        searchInput.sendKeys("Mac");
        WebElement searchBtn = driver.findElement(By.xpath("//button[contains(text(), 'Найти')]"));
        searchBtn.click();

        WebElement firstProduct = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='goods-title__heading']")));
        String firstProductTitleText = firstProduct.getText();
        firstProduct.click();


    }

    @AfterMethod
    public void after() {
        driver.quit();
    }
}
