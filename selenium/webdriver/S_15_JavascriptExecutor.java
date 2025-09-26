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

public class S_15_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        jsExecutor = (JavascriptExecutor) driver;
        emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
    }

    @Test
    public void TC_01() {
        navigateToUrlByJS("https://live.techpanda.org/index.php/mobile.html");
        sleepForSeconds(2);

        Assert.assertEquals(executeJS("return document.URL;"), "https://live.techpanda.org/index.php/mobile.html");
        Assert.assertEquals(executeJS("return document.title;"), "Mobile");

        highlightElementByJS("//a[@title='IPhone']/img");
        Assert.assertTrue(isImageDisplayedByJS("//a[@title='IPhone']/img"));

        scrollElementIntoViewTopByJS("//a[text()='About Us']");
        highlightElementByJS("//a[text()='About Us']");
        clickElementByJS("//a[text()='About Us']");
        sleepForSeconds(2);

        scrollElementIntoViewBottomByJS("//input[@id='newsletter']");
        highlightElementByJS("//input[@id='newsletter']");

        setElementAttributeByJS("//input[@id='newsletter']", "value", emailAddress);
        sleepForSeconds(1);

        Assert.assertEquals(getElementAttributeByJS("//input[@id='newsletter']", "value"), emailAddress);

        removeElementAttributeByJS("//input[@id='newsletter']", "value");
        sleepForSeconds(1);

        sendKeysToElementByJS("//input[@id='newsletter']", emailAddress);
        sleepForSeconds(1);

        highlightElementByJS("//button[@title='Subscribe']");
        clickElementByJS("//button[@title='Subscribe']");

        driver.switchTo().alert().accept();
        sleepForSeconds(2);

        highlightElementByJS("//li[@class='success-msg']//span");
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"), "Thank you for your subscription.");
        Assert.assertTrue(getInnerTextByJS().contains("Thank you for your subscription."));
        Assert.assertTrue(isExpectedTextPresentByJS("Thank you for your subscription."));

        scrollToBottomPageByJS();
        sleepForSeconds(1);

        scrollToTopPageByJS();
        sleepForSeconds(1);
    }

    @Test
    public void TC_02() {
        navigateToUrlByJS("https://warranty.rode.com/login");
        sleepForSeconds(2);

        highlightElementByJS("//button[@type='submit']");
        clickElementByJS("//button[@type='submit']");
        sleepForSeconds(1);

        Assert.assertEquals(getElementValidationMessageByJS("//input[@id='email']"), "Please fill out this field.");

        highlightElementByJS("//input[@id='email']");
        sendKeysToElementByJS("//input[@id='email']", emailAddress);

        highlightElementByJS("//button[@type='submit']");
        clickElementByJS("//button[@type='submit']");
        sleepForSeconds(1);

        Assert.assertEquals(getElementValidationMessageByJS("//input[@id='password']"), "Please fill out this field.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int getRandomNumber() {
        return new Random().nextInt(10000);
    }

    public void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sleepForMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public Object executeJS(String javascript) {
        return jsExecutor.executeScript(javascript);
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = arguments[0];", url);
    }

    public String getInnerTextByJS() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextPresentByJS(String expectedText) {
        return (Boolean) jsExecutor.executeScript("return document.documentElement.innerText.includes(arguments[0]);", expectedText);
    }

    public void scrollToTopPageByJS() {
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    public void scrollToBottomPageByJS() {
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollElementIntoViewTopByJS(String xpathLocator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(xpathLocator));
    }

    public void scrollElementIntoViewBottomByJS(String xpathLocator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(xpathLocator));
    }

    public void clickElementByJS(String xpathLocator) {
        jsExecutor.executeScript("arguments[0].click();", getWebElement(xpathLocator));
    }

    public void sendKeysToElementByJS(String xpathLocator, String keysToSend) {
        jsExecutor.executeScript("arguments[0].value = arguments[1];" + "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" + "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", getWebElement(xpathLocator), keysToSend);
    }

    public String getElementTextByJS(String xpathLocator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getWebElement(xpathLocator));
    }

    public String getElementValidationMessageByJS(String xpathLocator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(xpathLocator));
    }

    public String getElementAttributeByJS(String xpathLocator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute(arguments[1]);", getWebElement(xpathLocator), attributeName);
    }

    public void setElementAttributeByJS(String xpathLocator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", getWebElement(xpathLocator), attributeName, attributeValue);
    }

    public void removeElementAttributeByJS(String xpathLocator, String attributeName) {
        jsExecutor.executeScript("arguments[0].removeAttribute(arguments[1]);", getWebElement(xpathLocator), attributeName);
    }

    public void highlightElementByJS(String xpathLocator) {
        WebElement element = getWebElement(xpathLocator);
        String originalStyle = element.getAttribute("style");
        if (originalStyle == null) {
            originalStyle = "";
        }
        for (int i = 0; i < 3; i++) {
            jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px dashed red;");
            sleepForMillis(300);
            jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
            sleepForMillis(300);
        }
    }

    public boolean isImageDisplayedByJS(String xpathLocator) {
        WebElement element = getWebElement(xpathLocator);
        boolean isVisible = element.isDisplayed();
        boolean isLoaded = (Boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth!='undefined' && arguments[0].naturalWidth>0;", element);
        return isVisible && isLoaded;
    }

}
