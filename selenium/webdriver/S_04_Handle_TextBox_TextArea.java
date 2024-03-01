package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_04_Handle_TextBox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAddress, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");

		firstName = "Dong";
		lastName = "Do";
		fullName = firstName + " " + lastName;
		emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
		password = "Selenium3@";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);

		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);

		driver.findElement(By.xpath("//button[@id='register-button']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");

		driver.findElement(By.xpath("//a[contains(@class,'register-continue-button')]")).click();
	}

	@Test
	public void TC_02_Login() {
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(@class,'login-button')]")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed());
	}

	@Test
	public void TC_03_Verify() {
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailAddress);
	}

	@Test
	public void TC_04_Contact_Us() {
		driver.findElement(By.xpath("//a[text()='Contact us']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FullName']")).getAttribute("value"), fullName);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailAddress);

		driver.findElement(By.xpath("//textarea[@id='Enquiry']")).sendKeys("Selenium\nJava\n3");

		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your enquiry has been successfully sent to the store owner.");
	}

	@Test
	public void TC_05_Logout() {
		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-register']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-login']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}

}
