package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class S_20_Selenium_Waits {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hard_Wait() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        Thread.sleep(6000);

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
    }

    @Test
    public void TC_02_Implicit_Wait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
    }

    @Test
    public void TC_03_Explicit_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button"))).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

        explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='finish']/h4"), "Hello World!"));
    }

    @Test
    public void TC_04_Fluent_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        getWebElementFluentWait("//div[@id='start']/button").click();

        Assert.assertEquals(getWebElementFluentWait("//div[@id='finish']/h4").getText(), "Hello World!");
    }

    @Test
    public void TC_05_Wait_For_Page_Loaded() {
        driver.get("https://admin-demo.nopcommerce.com");

        driver.findElement(By.xpath("//button[contains(@class,'login-button')]")).click();

        waitForPageLoaded();

        driver.findElement(By.xpath("//div[@id='navbarText']//a[text()='Logout']")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElementFluentWait(String xpathLocator) {
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        return fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath(xpathLocator));
            }
        });
    }

    public void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(driver -> "complete".equals(js.executeScript("return document.readyState")) && (Boolean) js.executeScript("return (window.jQuery == null) || (jQuery.active === 0);"));
    }
}
