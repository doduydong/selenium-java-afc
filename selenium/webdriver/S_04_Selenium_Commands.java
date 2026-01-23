package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class S_04_Selenium_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("");
    }

    @Test
    public void TC_01_WebDriver_Commands() {
        driver.get("");
        driver.close();
        driver.quit();

        driver.getCurrentUrl();
        driver.getTitle();
        driver.getPageSource();
        driver.getWindowHandle();
        driver.getWindowHandles();

        driver.manage().getCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        driver.navigate().to("");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        driver.switchTo().alert();
        driver.switchTo().frame("");
        driver.switchTo().window("");
    }

    @Test
    public void TC_02_WebElement_Commands() {
        WebElement element = driver.findElement(By.xpath(""));

        element.click();

        element.clear();
        element.sendKeys("");

        element.getText();
        element.getAttribute("");
        element.getCssValue("");
        element.getShadowRoot();

        element.getScreenshotAs(OutputType.BASE64);
        element.getScreenshotAs(OutputType.BYTES);
        element.getScreenshotAs(OutputType.FILE);

        element.isDisplayed();
        element.isEnabled();
        element.isSelected();

        element.submit();

        List<WebElement> elements = driver.findElements(By.xpath(""));

        elements.size();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
