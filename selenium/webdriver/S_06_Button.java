package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_06_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.fahasa.com/customer/account/create");
    }

    @Test
    public void TC_01_Button() {
        driver.findElement(By.xpath("//ul[@class='popup-login-tab']/li[contains(@class,'popup-login-tab-login')]/a")).click();

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));

        Assert.assertFalse(loginButton.isEnabled());

        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toUpperCase(), "#000000");

        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("dong.afc@gmail.com");

        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("SeJava4@");

        Assert.assertTrue(loginButton.isEnabled());

        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
