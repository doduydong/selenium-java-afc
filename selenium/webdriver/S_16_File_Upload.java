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

import java.io.File;
import java.time.Duration;
import java.util.List;

public class S_16_File_Upload {
    WebDriver driver;
    String seleniumImg = "Selenium.png";
    String javaImg = "Java.png";
    String testngImg = "TestNG.png";
    String uploadFilesFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles";
    String seleniumFilePath = uploadFilesFolderPath + File.separator + seleniumImg;
    String javaFilePath = uploadFilesFolderPath + File.separator + javaImg;
    String testngFilePath = uploadFilesFolderPath + File.separator + testngImg;
    String multipleFilesPath = seleniumFilePath + "\n" + javaFilePath + "\n" + testngFilePath;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
    }

    @Test
    public void TC_01_Single_File() {
        getWebElement("//input[@type='file']").sendKeys(seleniumFilePath);

        Assert.assertTrue(getWebElement("//p[@class='name' and text()='" + seleniumImg + "']").isDisplayed());

        getWebElement("//tbody//button[contains(@class,'start')]").click();
        sleepForSeconds(2);

        Assert.assertTrue(getWebElement("//a[text()='" + seleniumImg + "']").isDisplayed());

        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + seleniumImg + "']/img"));

        getWebElement("//tbody//button[contains(@class,'delete')]").click();
        sleepForSeconds(2);
    }

    @Test
    public void TC_02_Multiple_Files() {
        getWebElement("//input[@type='file']").sendKeys(multipleFilesPath);

        Assert.assertTrue(getWebElement("//p[@class='name' and text()='" + seleniumImg + "']").isDisplayed());
        Assert.assertTrue(getWebElement("//p[@class='name' and text()='" + javaImg + "']").isDisplayed());
        Assert.assertTrue(getWebElement("//p[@class='name' and text()='" + testngImg + "']").isDisplayed());

        List<WebElement> startBtns = getListWebElement("//tbody//button[contains(@class,'start')]");
        for (WebElement btn : startBtns) {
            btn.click();
            sleepForSeconds(2);
        }

        Assert.assertTrue(getWebElement("//a[text()='" + seleniumImg + "']").isDisplayed());
        Assert.assertTrue(getWebElement("//a[text()='" + javaImg + "']").isDisplayed());
        Assert.assertTrue(getWebElement("//a[text()='" + testngImg + "']").isDisplayed());

        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + seleniumImg + "']/img"));
        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + javaImg + "']/img"));
        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + testngImg + "']/img"));

        List<WebElement> deleteBtns = getListWebElement("//tbody//button[contains(@class,'delete')]");
        for (WebElement btn : deleteBtns) {
            btn.click();
            sleepForSeconds(2);
        }
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

    public boolean isImageDisplayedByJS(String xpathLocator) {
        WebElement element = getWebElement(xpathLocator);
        boolean isVisible = element.isDisplayed();
        boolean isLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth!='undefined' && arguments[0].naturalWidth>0;", element);
        return isVisible && isLoaded;
    }

}
