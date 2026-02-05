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

public class S_14_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fix_Popup() {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepForSeconds(1);

        List<WebElement> popup = driver.findElements(By.xpath("//div[@role='dialog']"));
        Assert.assertTrue(popup.size() == 1);
        Assert.assertTrue(popup.get(0).isDisplayed());

        driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập']")).sendKeys("dong.afc@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("SeJava4@");
        driver.findElement(By.xpath("//div[@role='dialog']//button[contains(@class,'close')]")).click();
        sleepForSeconds(1);

        popup = driver.findElements(By.xpath("//div[@role='dialog']"));
        Assert.assertTrue(popup.isEmpty());
    }

    @Test
    public void TC_02_Random_Popup() {
        driver.get("https://vnk.edu.vn/");

        WebElement popup = driver.findElement(By.xpath("//div[contains(@class,'popmake-content')]"));
        sleepForSeconds(3);

        if (popup.isDisplayed()) {
            System.out.println("Popup is displayed");
            driver.findElement(By.xpath("//div[contains(@class,'popmake-content')]/following-sibling::button[contains(@class,'close')]")).click();
            sleepForSeconds(1);
        }

        Assert.assertFalse(popup.isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
