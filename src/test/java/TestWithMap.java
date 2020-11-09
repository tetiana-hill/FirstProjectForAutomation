import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestWithMap {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\IdeaProjects\\FirstProjectForAutomation\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = (new WebDriverWait(driver, 5));
        driver.get("https://www.rozetka.com.ua");

    }

    @Test
    public void rozetka() throws IOException {
        WebElement search = driver.findElement(By.name("search"));
        search.sendKeys("Mac");

        WebElement searchButton = driver.findElement(By.xpath("//button[contains(text(), 'Найти')]"));
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='goods-tile__title']")));
        Map<String, String> productTitleAndPrice = new LinkedHashMap<String, String>();
        List<WebElement> listingSearch = driver.findElements(By.xpath("//div[@class='goods-tile']"));

        for (WebElement product : listingSearch) {
            String title = product.findElement(By.className("goods-tile__title")).getText();
            String price = product.findElement(By.className("goods-tile__price-value")).getText();

            productTitleAndPrice.put(title, price);
        }

        FileWriter fw = new FileWriter("rozetkaTest.txt");
        for (Map.Entry<String, String> map : productTitleAndPrice.entrySet()) {
            fw.write(map.getKey() + " - " + map.getValue() + "\n");
        }
        fw.close();
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }
}
