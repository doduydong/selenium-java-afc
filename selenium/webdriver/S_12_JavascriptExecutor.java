package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_12_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(2);

		Assert.assertEquals(executeJS("return document.domain;"), "live.techpanda.org");
		Assert.assertEquals(executeJS("return document.title;"), "Home page");

		highlightElementByJS("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(2);

		highlightElementByJS("//a[@title='IPhone']/img");
		Assert.assertTrue(isImageLoadedByJS("//a[@title='IPhone']/img"));

		scrollElementIntoViewTopByJS("//a[text()='About Us']");
		highlightElementByJS("//a[text()='About Us']");
		clickToElementByJS("//a[text()='About Us']");
		sleepInSecond(2);

		Assert.assertEquals(executeJS("return document.title;"), "About Us");

		scrollElementIntoViewBottomByJS("//input[@id='newsletter']");
		highlightElementByJS("//input[@id='newsletter']");

		sendKeysToElementByJS("//input[@id='newsletter']", emailAddress);
		sleepInSecond(1);

		removeElementAttributeInDOMByJS("//input[@id='newsletter']", "value");
		sleepInSecond(1);

		setElementAttributeInDOMByJS("//input[@id='newsletter']", "value", emailAddress);
		sleepInSecond(1);

		Assert.assertEquals(getElementAttributeInDOMByJS("//input[@id='newsletter']", "value"), emailAddress);

		highlightElementByJS("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(2);

		highlightElementByJS("//li[@class='success-msg']//span");
		Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"), "Thank you for your subscription.");
		Assert.assertTrue(getInnerTextByJS().contains("Thank you for your subscription."));
		Assert.assertTrue(isExpectedTextInInnerTextByJS("Thank you for your subscription."));

		scrollToBottomByJS();
	}

	@Test
	public void TC_02() {
		navigateToUrlByJS("https://warranty.rode.com/login");
		sleepInSecond(2);

		highlightElementByJS("//button[@type='submit']");
		clickToElementByJS("//button[@type='submit']");
		sleepInSecond(1);

		Assert.assertEquals(getElementValidationMessageByJS("//input[@id='email']"), "Please fill out this field.");

		highlightElementByJS("//input[@id='email']");
		sendKeysToElementByJS("//input[@id='email']", emailAddress);

		highlightElementByJS("//button[@type='submit']");
		clickToElementByJS("//button[@type='submit']");
		sleepInSecond(1);

		Assert.assertEquals(getElementValidationMessageByJS("//input[@id='password']"), "Please fill out this field.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}

	public WebElement getWebElement(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	public Object executeJS(String javascript) {
		return jsExecutor.executeScript(javascript);
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "';");
	}

	public String getInnerTextByJS() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerTextByJS(String expectedText) {
		return jsExecutor.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0];").equals(expectedText);
	}

	public void scrollToBottomByJS() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");
	}

	public void scrollElementIntoViewTopByJS(String xpathLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(xpathLocator));
	}

	public void scrollElementIntoViewBottomByJS(String xpathLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(xpathLocator));
	}

	public void clickToElementByJS(String xpathLocator) {
		jsExecutor.executeScript("arguments[0].click();", getWebElement(xpathLocator));
	}

	public void sendKeysToElementByJS(String xpathLocator, String keysToSend) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + keysToSend + "');", getWebElement(xpathLocator));
	}

	public String getElementTextByJS(String xpathLocator) {
		return (String) jsExecutor.executeScript("return arguments[0].innerText;", getWebElement(xpathLocator));
	}

	public String getElementValidationMessageByJS(String xpathLocator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(xpathLocator));
	}

	public String getElementAttributeInDOMByJS(String xpathLocator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(xpathLocator));
	}

	public void setElementAttributeInDOMByJS(String xpathLocator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(xpathLocator));
	}

	public void removeElementAttributeInDOMByJS(String xpathLocator, String attributeName) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeName + "');", getWebElement(xpathLocator));
	}

	public void highlightElementByJS(String xpathLocator) {
		WebElement element = getWebElement(xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; border-style: dashed;');", element);
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
	}

	public boolean isImageLoadedByJS(String xpathLocator) {
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0;", getWebElement(xpathLocator));
	}

}
