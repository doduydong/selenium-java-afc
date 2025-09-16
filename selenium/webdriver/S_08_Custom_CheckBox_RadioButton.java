package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_08_Custom_CheckBox_RadioButton {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://login.ubuntu.com/");
    }

    @Test
    public void TC_01_Custom_CheckBox_RadioButton() {
        String returnUserRadio = "//input[@id='id_returning_user']";
        String newUserRadioButton = "//input[@id='id_new_user']";
        String acceptCheckBox = "//input[@id='id_accept_tos']";

        Assert.assertTrue(getWebElement(returnUserRadio).isSelected());
        Assert.assertFalse(getWebElement(newUserRadioButton).isSelected());

        clickElementByJS(newUserRadioButton);

        Assert.assertFalse(getWebElement(returnUserRadio).isSelected());
        Assert.assertTrue(getWebElement(newUserRadioButton).isSelected());

        Assert.assertFalse(getWebElement(acceptCheckBox).isSelected());

        clickElementByJS(acceptCheckBox);

        Assert.assertTrue(getWebElement(acceptCheckBox).isSelected());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public void clickElementByJS(String xpathLocator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(xpathLocator));
    }

}
