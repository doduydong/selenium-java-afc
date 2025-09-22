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
import java.util.List;

public class S_12_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_Not_In_DOM() {
        driver.get("https://ngoaingu24h.vn/");

        getWebElement("//button[text()='Đăng nhập']").click();
        sleepForSeconds(1);

        List<WebElement> popup = getListWebElement("//h2[text()='Đăng nhập']/parent::div[@role='dialog']");
        Assert.assertEquals(popup.size(), 1);
        Assert.assertTrue(popup.get(0).isDisplayed());

        getWebElement("//h2[text()='Đăng nhập']/button[contains(@class,'close-btn')]").click();
        sleepForSeconds(1);

        Assert.assertEquals(getListWebElement("//h2[text()='Đăng nhập']/parent::div[@role='dialog']").size(), 0);
    }

    @Test
    public void TC_02_Random_Popup_In_DOM() {
        driver.get("https://vnk.edu.vn/");
        sleepForSeconds(5);

        WebElement popup = getWebElement("//div[contains(@class,'popmake-content')]");

        if (popup.isDisplayed()) {
            System.out.println("Popup is displayed");
            getWebElement("//button[contains(@class,'popmake-close')]").click();
            sleepForSeconds(1);
        } else {
            System.out.println("Popup is not displayed");
        }

        Assert.assertFalse(getWebElement("//div[contains(@class,'popmake-content')]").isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public List<WebElement> getListWebElement(String xpathLocator) {
        return driver.findElements(By.xpath(xpathLocator));
    }

    public void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
