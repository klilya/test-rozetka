import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

public class Rozetka {
    WebDriver driver;

    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://rozetka.com.ua/");
    }

    @Test
    public void firstTest() {


        WebElement inputSearch = driver.findElement(By.xpath("//input[@name='search']"));
        inputSearch.sendKeys("Mac");
        WebElement searchButton = driver.findElement(By.xpath("//button[text() = ' Найти ']"));
        searchButton.click();

        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"goods-tile__inner\"]")));

        WebElement titleOfFirstItem = driver.findElement(By.xpath("//a[@class=\"goods-tile__heading ng-star-inserted\"]"));
        String titleOfFirstItemText = titleOfFirstItem.getText().trim();
        String titleOfFirstItemTextExpected = "Бокал для шампанского Chef&Sommelier 300 мл серия Macaron (L9348)";

        assertEquals(titleOfFirstItemTextExpected, titleOfFirstItemText, "Title doesn't equal");

    }

    @AfterMethod
    public void after() {
        driver.quit();
    }

}