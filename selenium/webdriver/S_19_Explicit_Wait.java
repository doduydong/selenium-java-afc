package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_19_Explicit_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Explicit_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Start']"))).click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='finish']/h4"), "Hello World!")));
    }

    @Test
    public void TC_02_Mix_Implicit_And_Explicit_Wait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement("//button[text()='Start']"))).click();

        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOf(getWebElement("//div[@id='finish']/h4"))).getText(), "Hello World!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

}
