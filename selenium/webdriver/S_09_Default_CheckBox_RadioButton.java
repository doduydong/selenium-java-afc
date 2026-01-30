package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_09_Default_CheckBox_RadioButton {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Default_CheckBox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        WebElement checkBox = getWebElement("//label[text()='Luggage compartment cover']/preceding-sibling::span/input[@type='checkbox']");

        Assert.assertFalse(checkBox.isSelected());

        checkDefaultCheckBoxOrRadioButton(checkBox);

        Assert.assertTrue(checkBox.isSelected());

        uncheckDefaultCheckBox(checkBox);

        Assert.assertFalse(checkBox.isSelected());
    }

    @Test
    public void TC_02_Default_RadioButton() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        WebElement radioBtn1 = getWebElement("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input[@type='radio']");
        WebElement radioBtn2 = getWebElement("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input[@type='radio']");

        Assert.assertTrue(radioBtn1.isSelected());
        Assert.assertFalse(radioBtn2.isSelected());

        checkDefaultCheckBoxOrRadioButton(radioBtn2);

        Assert.assertFalse(radioBtn1.isSelected());
        Assert.assertTrue(radioBtn2.isSelected());

        checkDefaultCheckBoxOrRadioButton(radioBtn1);

        Assert.assertTrue(radioBtn1.isSelected());
        Assert.assertFalse(radioBtn2.isSelected());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public void checkDefaultCheckBoxOrRadioButton(WebElement checkBox) {
        if (!checkBox.isSelected() && checkBox.isEnabled()) {
            checkBox.click();
            sleepForSeconds(1);
        }
    }

    public void uncheckDefaultCheckBox(WebElement checkBox) {
        if (checkBox.isSelected() && checkBox.isEnabled()) {
            checkBox.click();
            sleepForSeconds(1);
        }
    }

    public void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
