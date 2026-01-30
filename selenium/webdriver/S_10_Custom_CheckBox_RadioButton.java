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
import java.util.List;

public class S_10_Custom_CheckBox_RadioButton {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01() {
        driver.get("https://login.ubuntu.com/");

        WebElement radioBtn = driver.findElement(By.xpath("//input[@id='id_new_user']"));

        Assert.assertFalse(radioBtn.isSelected());

        jsExecutor.executeScript("arguments[0].click();", radioBtn);
        sleepForSeconds(1);

        Assert.assertTrue(radioBtn.isSelected());

        WebElement checkBox = driver.findElement(By.xpath("//input[@type='checkbox']"));

        Assert.assertFalse(checkBox.isSelected());

        jsExecutor.executeScript("arguments[0].click();", checkBox);
        sleepForSeconds(1);

        Assert.assertTrue(checkBox.isSelected());

        jsExecutor.executeScript("arguments[0].click();", checkBox);
        sleepForSeconds(1);

        Assert.assertFalse(checkBox.isSelected());
    }

    @Test
    public void TC_02() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        WebElement radioBtn = driver.findElement(By.xpath("//div[@role='radio' and @aria-label='Hà Nội']"));

        Assert.assertEquals(radioBtn.getAttribute("aria-checked"), "false");

        jsExecutor.executeScript("arguments[0].click();", radioBtn);

        Assert.assertEquals(radioBtn.getAttribute("aria-checked"), "true");

        List<WebElement> checkBoxes = driver.findElements(By.xpath("//div[@role='checkbox' and starts-with(@aria-label,'Quảng')]"));

        for (WebElement checkBox : checkBoxes) {
            Assert.assertEquals(checkBox.getAttribute("aria-checked"), "false");
        }

        for (WebElement checkBox : checkBoxes) {
            jsExecutor.executeScript("arguments[0].click();", checkBox);
            sleepForSeconds(1);
        }

        for (WebElement checkBox : checkBoxes) {
            Assert.assertEquals(checkBox.getAttribute("aria-checked"), "true");
        }
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
