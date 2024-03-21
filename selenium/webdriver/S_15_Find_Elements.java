package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_15_Find_Elements {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	// Affected by the 'implicit wait'

	@Test
	public void TC_01_Find_Element() {
		driver.findElement(By.xpath("//input[@id='email' or @id='pass']")).sendKeys("dongafc@gmail.com");
	}

	@Test
	public void TC_02_Find_Elements() {
		List<WebElement> elements;

		elements = driver.findElements(By.xpath("//input[@id='email' or @id='pass']"));
		Assert.assertEquals(elements.size(), 2);

		elements = driver.findElements(By.xpath("//input[@name='reg_email__']"));
		Assert.assertEquals(elements.size(), 0);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
