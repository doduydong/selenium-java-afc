package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_11_Frame_IFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Frame() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame(getWebElement("//frame[@name='login_page']"));

        getWebElement("//input[@name='fldLoginUserId']").sendKeys("D1310");

        getWebElement("//a[contains(@class,'login-btn')]").click();
        sleepForSeconds(1);

        getWebElement("//input[@type='password']").sendKeys("SeJava4@");

        getWebElement("//a[@id='loginBtn']").click();

        Assert.assertEquals(getWebElement("//p[contains(@class,'error-msg')]").getText(), "Customer ID/IPIN (Password) is invalid. Please try again.");
    }

    @Test
    public void TC_02_IFrame() {
        driver.get("https://toidicodedao.com/");

        scrollElementIntoView("//iframe[@title='fb:page Facebook Social Plugin']");
        driver.switchTo().frame(getWebElement("//iframe[@title='fb:page Facebook Social Plugin']"));

        String followers = getWebElement("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div").getText();
        System.out.println("Followers: " + followers);

        driver.switchTo().defaultContent();

        scrollElementIntoView("//div[@id='content-sidebar']//input[@class='search-field']");
        getWebElement("//div[@id='content-sidebar']//input[@class='search-field']").sendKeys("Selenium Java");
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

    public void scrollElementIntoView(String xpathLocator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(xpathLocator));
        sleepForSeconds(1);
    }

}
