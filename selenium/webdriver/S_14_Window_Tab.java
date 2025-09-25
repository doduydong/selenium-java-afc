package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class S_14_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Window() {
        driver.get("https://live.techpanda.org/index.php/mobile.html");

        String pageID = driver.getWindowHandle();
        String pageTitle = driver.getTitle();

        getWebElement("//a[text()='IPhone']/parent::h2/following-sibling::div//a[text()='Add to Compare']").click();
        sleepForSeconds(1);

        Assert.assertEquals(getWebElement("//li[@class='success-msg']").getText(), "The product IPhone has been added to comparison list.");

        getWebElement("//button[@title='Compare']").click();
        sleepForSeconds(1);

        switchToOtherWindowByCurrentID(pageID);

        Assert.assertTrue(getWebElement("//h1[text()='Compare Products']").isDisplayed());

        driver.manage().window().maximize();

        getWebElement("//button[@title='Close Window']").click();

        switchToWindowByTitle(pageTitle);

        getWebElement("//a[text()='Clear All']").click();

        driver.switchTo().alert().accept();
        sleepForSeconds(1);

        Assert.assertEquals(getWebElement("//li[@class='success-msg']").getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_02_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String pageID = driver.getWindowHandle();
        String pageTitle = driver.getTitle();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement("//legend[text()='Window']"));
        sleepForSeconds(1);

        getWebElement("//legend[text()='Window']/following-sibling::a[text()='GOOGLE']").click();
        sleepForSeconds(1);

        switchToOtherWindowByCurrentID(pageID);

        Assert.assertEquals(driver.getTitle(), "Google");

        switchToWindowByTitle(pageTitle);

        Assert.assertEquals(driver.getTitle(), "Selenium WebDriver");

        getWebElement("//legend[text()='Window']/following-sibling::a[text()='FACEBOOK']").click();
        sleepForSeconds(1);

        switchToWindowByTitle("Facebook – log in or sign up");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

        Assert.assertEquals(getNumberOfWindows(), 3);

        closeAllWindowsExceptWindowByID(pageID);

        Assert.assertEquals(getNumberOfWindows(), 1);
    }

    @Test
    public void TC_03_New_Window_Tab() {
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://www.google.com/");

        String googleTitle = driver.getTitle();

        Assert.assertEquals(googleTitle, "Google");

        switchToOtherWindowByCurrentID(driver.getWindowHandle());

        driver.close();

        switchToWindowByTitle(googleTitle);

        driver.switchTo().newWindow(WindowType.TAB).get("https://www.facebook.com/");

        Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

        Assert.assertEquals(getNumberOfWindows(), 2);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToOtherWindowByCurrentID(String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }
    }

    public void switchToWindowByTitle(String windowTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(windowTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowsExceptWindowByID(String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }

    public int getNumberOfWindows() {
        return driver.getWindowHandles().size();
    }

}
