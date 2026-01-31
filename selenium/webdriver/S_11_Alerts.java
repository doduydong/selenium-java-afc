package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_11_Alerts {
    WebDriver driver;
    Alert alert;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void TC_01_Alert() {
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();

        alert = driver.switchTo().alert();
        sleepForSeconds(1);

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You successfully clicked an alert");
    }

    @Test
    public void TC_02_Confirm() {
        WebElement jsConfirmBtn = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        jsConfirmBtn.click();

        alert = driver.switchTo().alert();
        sleepForSeconds(1);

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");

        jsConfirmBtn.click();

        alert = driver.switchTo().alert();
        sleepForSeconds(1);

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.accept();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Ok");
    }

    @Test
    public void TC_03_Prompt() {
        String keysToSend = "Selenium Java";

        WebElement jsPromptBtn = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));
        jsPromptBtn.click();

        alert = driver.switchTo().alert();
        sleepForSeconds(1);

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        alert.sendKeys("");
        alert.dismiss();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: null");

        jsPromptBtn.click();

        alert = driver.switchTo().alert();
        sleepForSeconds(1);

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        alert.sendKeys("");
        alert.accept();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered:");

        jsPromptBtn.click();

        alert = driver.switchTo().alert();
        sleepForSeconds(1);

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        alert.sendKeys(keysToSend);
        alert.accept();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + keysToSend);
    }

    @Test
    public void TC_04_Authentication_Alert() {
        driver.get("https://the-internet.herokuapp.com/");

        String basicAuthLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        String authLink = basicAuthLink.replace("//", "//admin:admin@");

        driver.get(authLink);

        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
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
