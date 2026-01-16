package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_02_Selenium_Locators {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_By_ID() {
        driver.findElement(By.id("email"));
    }

    @Test
    public void TC_02_By_Class() {
        driver.findElement(By.className("fb_logo"));
    }

    @Test
    public void TC_03_By_Name() {
        driver.findElement(By.name("login"));
    }

    @Test
    public void TC_04_By_Tag_Name() {
        driver.findElement(By.tagName("input"));
    }

    @Test
    public void TC_05_By_Link_Text() {
        driver.findElement(By.linkText("Tiếng Việt"));
    }

    @Test
    public void TC_06_By_Partial_Link_Text() {
        driver.findElement(By.partialLinkText("g V"));
    }

    @Test
    public void TC_07_By_XPath() {
        driver.findElement(By.xpath("//input[@id='email']"));
    }

    @Test
    public void TC_08_By_CSS() {
        driver.findElement(By.cssSelector("input[id='email']"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
