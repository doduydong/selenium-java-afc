package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_17_Implicit_Wait {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_No_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        getWebElement("//button[text()='Start']").click();

        Assert.assertTrue(getWebElement("//div[@id='finish']/h4[text()='Hello World!']").isDisplayed());
    }

    @Test
    public void TC_02_Short_Timeout() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        getWebElement("//button[text()='Start']").click();

        Assert.assertTrue(getWebElement("//div[@id='finish']/h4[text()='Hello World!']").isDisplayed());
    }

    @Test
    public void TC_03_Long_Timeout() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        getWebElement("//button[text()='Start']").click();

        Assert.assertTrue(getWebElement("//div[@id='finish']/h4[text()='Hello World!']").isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

}
