package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class S_12_User_Interactions {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Hover_Mouse() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
        sleepForSeconds(1);

        WebElement tooltip = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']"));
        Assert.assertTrue(tooltip.isDisplayed());
        Assert.assertEquals(tooltip.getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Click_And_Hold_Multi_Select() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> selectableItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        action.clickAndHold(selectableItems.get(0)).moveToElement(selectableItems.get(9)).release().perform();
        sleepForSeconds(1);

        List<WebElement> selectedItems = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'selected')]"));

        Assert.assertEquals(selectedItems.size(), 6);
    }

    @Test
    public void TC_03_Click_Random_Select() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> selectableItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        action.click(selectableItems.get(12)).keyDown(Keys.CONTROL).click(selectableItems.get(9)).keyUp(Keys.CONTROL).perform();
        sleepForSeconds(1);

        List<WebElement> selectedItems = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'selected')]"));

        Assert.assertEquals(selectedItems.size(), 2);
    }

    @Test
    public void TC_04_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement button = driver.findElement(By.xpath("//button[text()='Double click me']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        sleepForSeconds(1);

        action.doubleClick(button).perform();
        sleepForSeconds(1);

        WebElement message = driver.findElement(By.xpath("//p[@id='demo']"));
        Assert.assertTrue(message.isDisplayed());
        Assert.assertEquals(message, "Hello Automation Guys!");
    }

    @Test
    public void TC_05_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        WebElement button = driver.findElement(By.xpath("//span[text()='right click me']"));
        WebElement quitContext = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));

        Assert.assertFalse(quitContext.isDisplayed());

        action.contextClick(button).perform();
        sleepForSeconds(1);

        Assert.assertTrue(quitContext.isDisplayed());

        action.click(quitContext).perform();
        sleepForSeconds(1);

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "clicked: quit");
        alert.accept();
    }

    @Test
    public void TC_06_Drag_And_Drop() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement draggable = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement droptarget = driver.findElement(By.xpath("//div[@id='droptarget']"));

        Assert.assertEquals(droptarget.getText(), "Drag the small circle here.");

        action.dragAndDrop(draggable, droptarget).perform();
        sleepForSeconds(1);

        Assert.assertEquals(droptarget.getText(), "You did great!");
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
