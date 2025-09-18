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

public class S_10_User_Interactions {
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
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        action.moveToElement(getWebElement("//label[text()='Your age:']/following-sibling::input[@id='age']")).perform();
        sleepForSeconds(1);

        Assert.assertEquals(getWebElement("//div[@role='tooltip']/div[@class='ui-tooltip-content']").getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Drag_Selection() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> selectable = getListWebElement("//ol[@id='selectable']/li");

        action.clickAndHold(selectable.get(0)).moveToElement(selectable.get(15)).release().perform();
        sleepForSeconds(1);

        List<WebElement> selected = getListWebElement("//ol[@id='selectable']/li[contains(@class,'selected')]");

        Assert.assertEquals(selected.size(), 16);
    }

    @Test
    public void TC_03_Random_Selection() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> selectable = getListWebElement("//ol[@id='selectable']/li");

        action.click(selectable.get(10)).keyDown(Keys.CONTROL).click(selectable.get(16)).keyUp(Keys.CONTROL).perform();
        sleepForSeconds(1);

        List<WebElement> selected = getListWebElement("//ol[@id='selectable']/li[contains(@class,'selected')]");

        Assert.assertEquals(selected.size(), 2);
    }

    @Test
    public void TC_04_Double_Click() {
        driver.get("https://qa-practice.netlify.app/double-click");

        action.doubleClick(getWebElement("//button[@id='double-click-btn']")).perform();
        sleepForSeconds(1);

        Assert.assertTrue(getWebElement("//div[@id='double-click-result' and text()='Congrats, you double clicked!']").isDisplayed());
    }

    @Test
    public void TC_05_Right_Click() {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        Assert.assertFalse(getWebElement("//ul[contains(@class,'context-menu-list')]").isDisplayed());

        action.contextClick(getWebElement("//span[text()='right click me']")).perform();
        sleepForSeconds(1);

        Assert.assertTrue(getWebElement("//ul[contains(@class,'context-menu-list')]").isDisplayed());

        action.click(getWebElement("//ul[contains(@class,'context-menu-list')]/li[contains(@class,'context-menu-icon-quit')]")).perform();
        sleepForSeconds(1);

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "clicked: quit");
        alert.accept();
    }

    @Test
    public void TC_06_Drag_Drop() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement source = getWebElement("//div[@id='draggable']");
        WebElement target = getWebElement("//div[@id='droptarget']");

        Assert.assertEquals(target.getText(), "Drag the small circle here.");

        action.dragAndDrop(source, target).perform();
        sleepForSeconds(1);

        Assert.assertEquals(target.getText(), "You did great!");
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

}
