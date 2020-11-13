import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageLogic {
    By firstProduct = By.xpath("//a[@class='header-topLine__user-link link-dashed']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SearchPageLogic(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String firstProductTitleText(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
        return driver.findElement(firstProduct).getText();
    }

    public ProductPageLogic firstProductClick() {
        driver.findElement(firstProduct).click();
        return new ProductPageLogic(driver);
    }
}
