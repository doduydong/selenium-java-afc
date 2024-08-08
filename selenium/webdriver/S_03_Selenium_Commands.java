package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_03_Selenium_Commands {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
	}

	@Test
	public void TC_01_WebDriver_Commands() {
		driver.get("");
		driver.close();
		driver.quit();

		driver.getCurrentUrl();
		driver.getPageSource();
		driver.getTitle();
		driver.getWindowHandle();
		driver.getWindowHandles();

		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.manage().window().fullscreen();

		driver.manage().window().getPosition();
		driver.manage().window().getSize();

		driver.navigate().to("");
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();

		driver.switchTo().alert();
		driver.switchTo().frame("");
		driver.switchTo().window("");
	}

	@Test
	public void TC_02_WebElement_Commands() {
		WebElement element = driver.findElement(By.xpath(""));

		element.click();

		element.clear();
		element.sendKeys("");

		element.getAttribute("");
		element.getCssValue("");
		element.getTagName();
		element.getText();

		element.getLocation();
		element.getSize();
		element.getRect();

		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);

		element.isDisplayed();
		element.isEnabled();
		element.isSelected();

		element.submit();

		List<WebElement> elements = driver.findElements(By.xpath(""));

		elements.get(0);
		elements.isEmpty();
		elements.size();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
