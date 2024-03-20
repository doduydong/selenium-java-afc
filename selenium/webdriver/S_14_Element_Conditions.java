package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_14_Element_Conditions {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Visible() {
		// Element is present on the DOM of a page and visible

		driver.findElement(By.xpath("//a[text()='Create new account']")).click();

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("dongafc@gmail.com");

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_02_Invisible() {
		// Element is either invisible or not present on the DOM

		driver.findElement(By.xpath("//input[@name='reg_email__']")).clear();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		driver.findElement(By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']/img")).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_03_Presence() {
		// Element is present on the DOM of a page

		driver.findElement(By.xpath("//a[text()='Create new account']")).click();

		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email__']")));

		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_04_Staleness() {
		// Element is no longer attached to the DOM

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='reg_email__']"));

		driver.findElement(By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']/img")).click();

		explicitWait.until(ExpectedConditions.stalenessOf(emailTextbox));

		explicitWait.until(ExpectedConditions.invisibilityOf(emailTextbox));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
