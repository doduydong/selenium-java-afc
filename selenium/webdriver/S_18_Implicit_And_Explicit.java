package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_18_Implicit_And_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Found_Element_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 3);

		driver.findElement(By.xpath("//input[@id='email']"));

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
	}

	@Test
	public void TC_02_Not_Found_Element_Implicit() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@name='reg_email__']"));
	}

	@Test
	public void TC_03_Not_Found_Element_Explicit() {
		explicitWait = new WebDriverWait(driver, 3);

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));
	}

	@Test
	public void TC_04_Not_Found_Element_Explicit_By_WebElement_Param() {
		// Apply Implicit Wait to find element(s)
		// (NoSuchElementException) - If no matching elements are found
		// Found element(s) - (TimeoutException) - If expected condition failed

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 3);

		// * NoSuchElementException
		// explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='reg_email__']"))));

		driver.findElement(By.xpath("//a[text()='Create new account']")).click();

		// * TimeoutException
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"))));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
