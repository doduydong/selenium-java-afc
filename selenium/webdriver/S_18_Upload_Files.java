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

public class S_18_Upload_Files {
    WebDriver driver;
    String uploadFilesFolder = System.getProperty("user.dir") + "\\uploadFiles\\";
    String javaFile = "Java.jpg";
    String seleniumFile = "Selenium.jpg";
    String testngFile = "TestNG.jpg";
    String javaFilePath = uploadFilesFolder + javaFile;
    String seleniumFilePath = uploadFilesFolder + seleniumFile;
    String testngFilePath = uploadFilesFolder + testngFile;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
    }

    @Test
    public void TC_01_Single_File() {
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(seleniumFilePath);

        Assert.assertTrue(driver.findElement(By.xpath("//td//p[text()='" + seleniumFile + "']")).isDisplayed());

        driver.findElement(By.xpath("//td//button[contains(@class,'start')]")).click();
        sleepForSeconds(1);

        Assert.assertTrue(driver.findElement(By.xpath("//td//a[text()='" + seleniumFile + "']")).isDisplayed());

        Assert.assertTrue(isImageLoaded("//td//a[@title='" + seleniumFile + "']/img"));

        driver.findElement(By.xpath("//td//button[contains(@class,'delete')]")).click();
        sleepForSeconds(1);
    }

    @Test
    public void TC_02_Multiple_Files() {
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(javaFilePath + "\n" + seleniumFilePath + "\n" + testngFilePath);

        Assert.assertTrue(driver.findElement(By.xpath("//td//p[text()='" + javaFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//td//p[text()='" + seleniumFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//td//p[text()='" + testngFile + "']")).isDisplayed());

        List<WebElement> startBtns = driver.findElements(By.xpath("//td//button[contains(@class,'start')]"));
        for (WebElement startBtn : startBtns) {
            startBtn.click();
            sleepForSeconds(1);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//td//a[text()='" + javaFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//td//a[text()='" + seleniumFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//td//a[text()='" + testngFile + "']")).isDisplayed());

        Assert.assertTrue(isImageLoaded("//td//a[@title='" + javaFile + "']/img"));
        Assert.assertTrue(isImageLoaded("//td//a[@title='" + seleniumFile + "']/img"));
        Assert.assertTrue(isImageLoaded("//td//a[@title='" + testngFile + "']/img"));

        List<WebElement> deleteBtns = driver.findElements(By.xpath("//td//button[contains(@class,'delete')]"));
        for (WebElement deleteBtn : deleteBtns) {
            deleteBtn.click();
            sleepForSeconds(1);
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

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public boolean isImageLoaded(String xpathLocator) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0;", getWebElement(xpathLocator));
    }
}
