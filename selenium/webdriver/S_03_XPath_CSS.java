package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_03_XPath_CSS {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/basic-form/");
    }

    @Test
    public void TC_01_XPath() {
        driver.findElement(By.xpath("//h5[contains(text(),'Hello World')]"));

        driver.findElement(By.xpath("//h5/p[starts-with(text(),'Mail Personal')]"));

        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What', \"'s happened?\")]"));

        driver.findElement(By.xpath("//a[contains(text(),'Quảng') and contains(text(),'Nam')]"));

        driver.findElement(By.xpath("//a[contains(text(),'Bình') or contains(text(),'Nam')]"));

        driver.findElement(By.xpath("//div[@class='container']/button[not(@id='button-print')]"));

        driver.findElement(By.xpath("//legend[text()='Window']/following-sibling::a[1]"));

        driver.findElement(By.xpath("//legend[text()='Window']/following-sibling::a[position()=2]"));

        driver.findElement(By.xpath("//legend[text()='Window']/following-sibling::a[last()]"));

        driver.findElement(By.xpath("//legend[text()='Window']/following-sibling::a[last()-1]"));

        driver.findElement(By.xpath("//legend[text()='Window']/following-sibling::a[text()='GOOGLE']"));

        driver.findElement(By.xpath("//a[text()='FACEBOOK']/preceding-sibling::a"));

        driver.findElement(By.xpath("//legend[text()='XPath Tip and Trick']/parent::div"));

        driver.findElement(By.xpath("//legend[text()='XPath Tip and Trick']/ancestor::body/h1"));
    }

    @Test
    public void TC_02_CSS() {
        // ID
        driver.findElement(By.cssSelector("input[id='email']"));
        driver.findElement(By.cssSelector("input#email"));
        driver.findElement(By.cssSelector("#email"));

        // Class
        driver.findElement(By.cssSelector("span[class='date']"));
        driver.findElement(By.cssSelector("span.date"));
        driver.findElement(By.cssSelector(".date"));

        driver.findElement(By.cssSelector("button[id*='enable']")); // Contains

        driver.findElement(By.cssSelector("button[id^='button-e']")); // Starts-with

        driver.findElement(By.cssSelector("button[id$='print']")); // Ends-with
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
