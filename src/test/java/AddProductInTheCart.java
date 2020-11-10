import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddProductInTheCart {
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
    public void testAddProductInTheCart() throws Exception {
        driver.findElement(By.linkText("Ноутбуки и компьютеры")).click();
        driver.findElement(By.linkText("Ноутбуки")).click();
        driver.findElement(By.cssSelector("button.buy-button.goods-tile__buy-button > svg")).click();
        driver.findElement(By.cssSelector("a.header-actions__button.header-actions__button_type_basket.header-actions__button_state_active > svg > use")).click();
        driver.findElement(By.xpath("//rz-cart-counter/div/input")).click();
        driver.findElement(By.xpath("//rz-cart-counter/div/input")).click();
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }
}
