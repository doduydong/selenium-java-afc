package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_19_Element_States {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Visible() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//button[@title='Login']")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
    }

    @Test
    public void TC_02_Invisible_Still_In_DOM() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//button[@title='Login']")).click();

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("dong.afc@gmail.com");

        driver.findElement(By.xpath("//button[@title='Login']")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
    }

    @Test
    public void TC_03_Invisible_Removed_From_DOM() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("dong.afc@gmail.com");

        driver.findElement(By.xpath("//button[@title='Login']")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
    }

    @Test
    public void TC_04_Presence() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//button[@title='Login']")).click();

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("dong.afc@gmail.com");

        driver.findElement(By.xpath("//button[@title='Login']")).click();

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
    }

    @Test
    public void TC_05_Staleness() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//button[@title='Login']")).click();

        WebElement emailErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));

        driver.navigate().refresh();

        explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
