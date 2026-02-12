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
import java.util.Random;

public class S_17_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("http://live.techpanda.org/");

        jsExecutor = (JavascriptExecutor) driver;
        emailAddress = "dong.afc" + getRandomNumber() + "@gmail.com";
    }

    @Test
    public void TC_01() {
        Assert.assertEquals(executeJavaScript("return document.domain;"), "live.techpanda.org");

        scrollElementToTop("//a[text()='Mobile']");
        clickElement("//a[text()='Mobile']");
        sleepForSeconds(3);

        highlightElement("//a[@title='IPhone']/img");
        Assert.assertTrue(isImageLoaded("//a[@title='IPhone']/img"));

        scrollElementToBottom("//input[@id='newsletter']");
        setAttributeInDOM("//input[@id='newsletter']", "value", "Selenium Java");
        sleepForSeconds(1);

        Assert.assertEquals(getAttributeInDOM("//input[@id='newsletter']", "value"), "Selenium Java");

        clickElement("//button[@title='Subscribe']");
        sleepForSeconds(1);

        Assert.assertEquals(getElementValidationMessage("//input[@id='newsletter']"), "Please enter an email address.");

        removeAttributeInDOM("//input[@id='newsletter']", "value");
        sleepForSeconds(1);

        sendKeysToElement("//input[@id='newsletter']", emailAddress);
        sleepForSeconds(1);

        clickElement("//button[@title='Subscribe']");
        sleepForSeconds(3);

        Assert.assertEquals(getElementText("//li[@class='success-msg']//span"), "Thank you for your subscription.");

        scrollToBottomOfPage();
        sleepForSeconds(1);

        scrollToTopOfPage();
        sleepForSeconds(1);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public Object executeJavaScript(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public void scrollToTopOfPage() {
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    public void scrollToBottomOfPage() {
        jsExecutor.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
    }

    public void scrollElementToTop(String xpathLocator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'start'});", getWebElement(xpathLocator));
    }

    public void scrollElementToBottom(String xpathLocator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'end'});", getWebElement(xpathLocator));
    }

    public void highlightElement(String xpathLocator) {
        WebElement element = getWebElement(xpathLocator);
        jsExecutor.executeScript("arguments[0].style.outline='2px dashed red';", element);
        sleepForSeconds(2);
        jsExecutor.executeScript("arguments[0].style.outline='';", element);
    }

    public void clickElement(String xpathLocator) {
        jsExecutor.executeScript("arguments[0].click();", getWebElement(xpathLocator));
    }

    public void sendKeysToElement(String xpathLocator, String keysToSend) {
        jsExecutor.executeScript("arguments[0].value = arguments[1];", getWebElement(xpathLocator), keysToSend);
    }

    public String getElementText(String xpathLocator) {
        return (String) jsExecutor.executeScript("return arguments[0].innerText;", getWebElement(xpathLocator));
    }

    public String getElementValidationMessage(String xpathLocator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(xpathLocator));
    }

    public String getAttributeInDOM(String xpathLocator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute(arguments[1]);", getWebElement(xpathLocator), attributeName);
    }

    public void setAttributeInDOM(String xpathLocator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", getWebElement(xpathLocator), attributeName, attributeValue);
    }

    public void removeAttributeInDOM(String xpathLocator, String attributeName) {
        jsExecutor.executeScript("arguments[0].removeAttribute(arguments[1]);", getWebElement(xpathLocator), attributeName);
    }

    public boolean isImageLoaded(String xpathLocator) {
        return (Boolean) jsExecutor.executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0;", getWebElement(xpathLocator));
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

    public int getRandomNumber() {
        return new Random().nextInt(10000);
    }
}
