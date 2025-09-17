package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_09_Alerts {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void TC_01_Accept_Alert() {
        getWebElement("//button[@onclick='jsAlert()']").click();

        Alert alert = switchToAlert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();

        Assert.assertEquals(getResultMessage(), "You successfully clicked an alert");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        WebElement confirmAlertButton = getWebElement("//button[@onclick='jsConfirm()']");

        confirmAlertButton.click();

        Alert confirmAlert = switchToAlert();

        Assert.assertEquals(confirmAlert.getText(), "I am a JS Confirm");

        confirmAlert.dismiss();

        Assert.assertEquals(getResultMessage(), "You clicked: Cancel");

        confirmAlertButton.click();

        confirmAlert = switchToAlert();

        Assert.assertEquals(confirmAlert.getText(), "I am a JS Confirm");

        confirmAlert.accept();

        Assert.assertEquals(getResultMessage(), "You clicked: Ok");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        String keysToSend = "Selenium Java";

        WebElement promptAlertButton = getWebElement("//button[@onclick='jsPrompt()']");

        promptAlertButton.click();

        Alert promptAlert = switchToAlert();

        Assert.assertEquals(promptAlert.getText(), "I am a JS prompt");

        promptAlert.dismiss();

        Assert.assertEquals(getResultMessage(), "You entered: null");

        promptAlertButton.click();

        promptAlert = switchToAlert();

        Assert.assertEquals(promptAlert.getText(), "I am a JS prompt");

        promptAlert.sendKeys(keysToSend);

        promptAlert.accept();

        Assert.assertEquals(getResultMessage(), "You entered: " + keysToSend);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    public String getResultMessage() {
        return driver.findElement(By.xpath("//h4[text()='Result:']/following-sibling::p[@id='result']")).getText();
    }

}
