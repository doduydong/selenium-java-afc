package webdriver;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_19_Fluent_Wait {
	WebDriver driver;
	FluentWait<WebDriver> fluentWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		getWebElement("//button[text()='Start']").click();

		Assert.assertEquals(getElementText("//div[@id='finish']/h4"), "Hello World!");
	}

	@Test
	public void TC_02() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		Assert.assertTrue(isElementDisplayed("//div[@id='javascript_countdown_time' and text()='01:01:01']"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public WebElement getWebElement(String xpathLocator) {
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
		return fluentWait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
	}

	public String getElementText(String xpathLocator) {
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
		return fluentWait.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator)).getText();
			}
		});
	}

	public Boolean isElementDisplayed(String xpathLocator) {
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
		return fluentWait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator)).isDisplayed();
			}
		});
	}

}
