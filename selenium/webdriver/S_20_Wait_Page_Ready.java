package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_20_Wait_Page_Ready {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("https://admin-demo.nopcommerce.com/login");

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='Email']"));
		emailTextbox.clear();
		emailTextbox.sendKeys("admin@yourstore.com");

		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='Password']"));
		passwordTextbox.clear();
		passwordTextbox.sendKeys("admin");

		driver.findElement(By.xpath("//button[contains(@class,'login-button')]")).click();

		waitForPageReady();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='content-header']/h1[contains(text(),'Dashboard')]")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void waitForPageReady() {
		explicitWait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (jQuery != null) && (jQuery.active === 0) && (document.readyState === 'complete');");
			}
		});
	}

}
