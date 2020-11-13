import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Lesson_25 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\IdeaProjects\\FirstProjectForAutomation\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = (new WebDriverWait(driver, 10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://rozetka.com.ua/");
    }
    @Test
    public void pageObject() throws IllegalAccessException {
        new MainPageLogic(driver, wait).typeTextSearchInput("Mac").findBtnClick();

        SearchPageLogic searchPageLogic = new SearchPageLogic(driver, wait);

        String title = searchPageLogic.firstProductTitleText();
        System.out.println(title);

        searchPageLogic.firstProductClick();
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }
}
