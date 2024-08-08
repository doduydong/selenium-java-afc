package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_02_Selenium_Locators {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Locators_By() {
		// Id
		driver.findElement(By.id("email"));

		// Class
		driver.findElement(By.className("fb_logo"));

		// Name
		driver.findElement(By.name("login"));

		// Tag name
		driver.findElements(By.tagName("input"));

		// Link text
		driver.findElement(By.linkText("Tiếng Việt"));

		// Partial link text
		driver.findElement(By.partialLinkText("iếng Việ"));
	}

	@Test
	public void TC_02_By_CSS() {
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input#email"));
		driver.findElement(By.cssSelector("#email"));

		driver.findElement(By.cssSelector("img[class='fb_logo _8ilh img']"));
		driver.findElement(By.cssSelector("img.fb_logo"));
		driver.findElement(By.cssSelector(".fb_logo"));

		driver.findElement(By.cssSelector("button[name='login']"));

		driver.findElements(By.cssSelector("input"));

		driver.findElement(By.cssSelector("a[title='Vietnamese']"));
	}

	@Test
	public void TC_03_By_XPath() {
		driver.findElement(By.xpath("//input[@id='email']"));

		driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']"));

		driver.findElement(By.xpath("//button[@name='login']"));

		driver.findElements(By.xpath("//input"));

		driver.findElement(By.xpath("//a[@title='Vietnamese']"));
		driver.findElement(By.xpath("//a[text()='Tiếng Việt']"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
