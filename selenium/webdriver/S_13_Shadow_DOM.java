package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_13_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/shadow-dom/");
    }

    @Test
    public void TC_01_Shadow_DOM() {
        // Shadow host
        SearchContext shadowHost = driver.findElement(By.xpath("//div[@id='shadow_host']")).getShadowRoot();

        System.out.println("Shadow content: " + shadowHost.findElement(By.cssSelector("span#shadow_content>span")).getText());

        System.out.println("Link value: " + shadowHost.findElement(By.cssSelector("a")).getAttribute("href"));

        shadowHost.findElement(By.cssSelector("input[type='text']")).sendKeys("Selenium Java");

        WebElement checkBox = shadowHost.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(checkBox.isSelected());
        if (!checkBox.isSelected()) {
            checkBox.click();
        }
        Assert.assertTrue(checkBox.isSelected());

        // Nested shadow
        SearchContext nestedShadow = shadowHost.findElement(By.cssSelector("div[id='nested_shadow_host']")).getShadowRoot();

        System.out.println("Nested shadow content: " + nestedShadow.findElement(By.cssSelector("div#nested_shadow_content>div")).getText());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
