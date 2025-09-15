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

public class S_07_Default_CheckBox_RadioButton {
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

        Assert.assertFalse(isDefaultCheckBoxOrRadioButtonSelected("//label[text()='Luggage compartment cover']/preceding-sibling::span/input[@type='checkbox']"));

        checkDefaultCheckBoxOrRadioButton("//label[text()='Luggage compartment cover']/preceding-sibling::span/input[@type='checkbox']");

        Assert.assertTrue(isDefaultCheckBoxOrRadioButtonSelected("//label[text()='Luggage compartment cover']/preceding-sibling::span/input[@type='checkbox']"));

        uncheckDefaultCheckBox("//label[text()='Luggage compartment cover']/preceding-sibling::span/input[@type='checkbox']");

        Assert.assertFalse(isDefaultCheckBoxOrRadioButtonSelected("//label[text()='Luggage compartment cover']/preceding-sibling::span/input[@type='checkbox']"));
    }

    @Test
    public void TC_02_Default_RadioButton() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        Assert.assertTrue(isDefaultCheckBoxOrRadioButtonSelected("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input[@type='radio']"));

        Assert.assertFalse(isDefaultCheckBoxOrRadioButtonSelected("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input[@type='radio']"));

        checkDefaultCheckBoxOrRadioButton("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input[@type='radio']");

        Assert.assertFalse(isDefaultCheckBoxOrRadioButtonSelected("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input[@type='radio']"));

        Assert.assertTrue(isDefaultCheckBoxOrRadioButtonSelected("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input[@type='radio']"));

        checkDefaultCheckBoxOrRadioButton("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::span/input[@type='radio']");

        Assert.assertFalse(isDefaultCheckBoxOrRadioButtonSelected("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input[@type='radio']"));

        Assert.assertTrue(isDefaultCheckBoxOrRadioButtonSelected("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::span/input[@type='radio']"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public void checkDefaultCheckBoxOrRadioButton(String checkBoxXPath) {
        WebElement checkBox = getWebElement(checkBoxXPath);
        if (checkBox.isEnabled() && !checkBox.isSelected()) {
            checkBox.click();
        }
    }

    public void uncheckDefaultCheckBox(String checkBoxXPath) {
        WebElement checkBox = getWebElement(checkBoxXPath);
        if (checkBox.isEnabled() && checkBox.isSelected()) {
            checkBox.click();
        }
    }

    public boolean isDefaultCheckBoxOrRadioButtonSelected(String checkBoxXPath) {
        return getWebElement(checkBoxXPath).isSelected();
    }

}
