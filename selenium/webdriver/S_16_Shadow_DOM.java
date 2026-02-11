package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_16_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() {
        driver.get("https://books-pwakit.appspot.com/");
        sleepForSeconds(3);

        WebElement bookAppShadowHost = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
        SearchContext bookAppShadowRoot = bookAppShadowHost.getShadowRoot();

        WebElement bookInputShadowHost = bookAppShadowRoot.findElement(By.cssSelector("book-input-decorator"));
        bookInputShadowHost.findElement(By.cssSelector("input#input")).sendKeys("Selenium Java");
        sleepForSeconds(1);

        SearchContext bookInputShadowRoot = bookInputShadowHost.getShadowRoot();
        bookInputShadowRoot.findElement(By.cssSelector("div.icon")).click();
    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        sleepForSeconds(3);

        WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        shadowRoot.findElement(By.cssSelector("input[type='text']")).sendKeys("Selenium Java");
        sleepForSeconds(1);

        WebElement checkBox = shadowRoot.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(checkBox.isSelected());
        checkBox.click();
        sleepForSeconds(1);
        Assert.assertTrue(checkBox.isSelected());
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
