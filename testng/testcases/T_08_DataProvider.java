package testcases;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class T_08_DataProvider {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(description = "Register & Login", dataProvider = "userData")
	public void Register_Login(String firstName, String lastName, String emailAddress, String password) {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);

		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);

		driver.findElement(By.xpath("//button[@id='register-button']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");

		driver.findElement(By.xpath("//a[contains(@class,'register-continue-button')]")).click();

		driver.findElement(By.xpath("//a[@class='ico-login']")).click();

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(@class,'login-button')]")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed());

		driver.findElement(By.xpath("//a[@class='ico-account']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailAddress);

		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-register']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-login']")).isDisplayed());
	}

	@DataProvider(name = "userData")
	public Object[][] userAccountData() {
		return new Object[][] {
				// Data #1
				{ "Dong", "Do", "dongafc" + getRandomNumber() + "@gmail.com", "SELENIUM3" },
				// Data #2
				{ "John", "Do", "johnafc" + getRandomNumber() + "@gmail.com", "selenium@" },
				// Data #3
				{ "YuDo", "Do", "yudoafc" + getRandomNumber() + "@gmail.com", "afcS3@" } };
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}

}
