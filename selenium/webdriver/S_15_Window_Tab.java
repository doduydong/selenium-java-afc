package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class S_15_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//legend[text()='Window']")));
        sleepForSeconds(1);

        String currentPageId = driver.getWindowHandle();
        String currentPageTitle = driver.getTitle();

        driver.findElement(By.xpath("//legend[text()='Window']/following-sibling::a[text()='GOOGLE']")).click();
        sleepForSeconds(1);

        switchToWindowDifferentWithCurrentById(currentPageId);

        driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("Selenium Java");
        sleepForSeconds(1);

        switchToWindowByPageTitle(currentPageTitle);

        driver.findElement(By.xpath("//legend[text()='Window']/following-sibling::a[text()='FACEBOOK']")).click();
        sleepForSeconds(1);

        switchToWindowByPageTitle("Facebook – log in or sign up");

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("dong.afc@gmail.com");
        sleepForSeconds(1);

        closeAllWindowsTabsExceptExpectedById(currentPageId);
    }

    @Test
    public void TC_02_Window() {
        driver.get("https://live.techpanda.org/index.php/mobile.html");

        String currentPageId = driver.getWindowHandle();
        String currentPageTitle = driver.getTitle();

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
        sleepForSeconds(1);
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
        sleepForSeconds(1);
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
        sleepForSeconds(1);

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        sleepForSeconds(1);

        switchToWindowDifferentWithCurrentById(currentPageId);
        driver.manage().window().maximize();
        System.out.println(2);

        driver.findElement(By.xpath("//button[@title='Close Window']")).click();
        sleepForSeconds(1);

        switchToWindowByPageTitle(currentPageTitle);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        driver.switchTo().alert().accept();
        sleepForSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_03_New_Window_Tab() {
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.google.com/");

        driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("Selenium Java");
        sleepForSeconds(1);

        driver.switchTo().newWindow(WindowType.WINDOW).get("https://www.facebook.com/");

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("dong.afc@gmail.com");
        sleepForSeconds(1);
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

    public void switchToWindowDifferentWithCurrentById(String windowId) {
        Set<String> windowIds = driver.getWindowHandles();
        for (String id : windowIds) {
            if (!id.equals(windowId)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByPageTitle(String pageTitle) {
        Set<String> windowIds = driver.getWindowHandles();
        for (String id : windowIds) {
            driver.switchTo().window(id);
            if (driver.getTitle().equals(pageTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowsTabsExceptExpectedById(String windowId) {
        Set<String> windowIds = driver.getWindowHandles();
        for (String id : windowIds) {
            if (!id.equals(windowId)) {
                driver.switchTo().window(id);
                sleepForSeconds(1);
                driver.close();
            }
        }
        driver.switchTo().window(windowId);
    }
}
