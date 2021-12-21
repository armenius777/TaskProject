import helpers.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;

    @BeforeSuite
    void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        openPage(Constants.URL);
    }

    @AfterSuite
    void quitDriver() {
        driver.close();
        driver.quit();
    }


     public void openPage(String URL) {
        driver.get(URL);
        driver.manage().window().maximize();
     }

     public void waitUntilElementIsVisible(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex) {
            System.out.println("Element is not shown");
        }
     }

     public void waitSeconds(int seconds) throws InterruptedException {
         TimeUnit.SECONDS.sleep(seconds);
     }

     public boolean isElementClickable(WebDriver driver, WebElement element) {
        boolean result = false;
        try {
            WebDriverWait wt = new WebDriverWait(driver,5);
            wt.until(ExpectedConditions.elementToBeClickable (element));
            result = true;
        } catch (Exception ex) {
            System.out.println("Element is not clickable");
        }
        return result;
     }

}
