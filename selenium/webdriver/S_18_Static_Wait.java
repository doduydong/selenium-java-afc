package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_18_Static_Wait {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Static_Wait_Short() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        getWebElement("//button[text()='Start']").click();

        sleepForSeconds(3);

        Assert.assertTrue(getWebElement("//div[@id='finish']/h4[text()='Hello World!']").isDisplayed());
    }

    @Test
    public void TC_02_Static_Wait_Long() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        getWebElement("//button[text()='Start']").click();

        sleepForSeconds(10);

        Assert.assertTrue(getWebElement("//div[@id='finish']/h4[text()='Hello World!']").isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
