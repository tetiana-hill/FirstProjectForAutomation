import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FirstTestForHW27 {
    WebDriver driver;

    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\IdeaProjects\\FirstProjectForAutomation\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @Test
    public void testGoogle() throws InterruptedException, IOException {
//        WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input"));
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("First Test");
//        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]"));
//        WebElement searchBtn = driver.findElement(By.id("SIvCob"));
        WebElement searchBtn = driver.findElement(By.xpath("(//input[@name='btnK'])[2]"));
        searchBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }
}
