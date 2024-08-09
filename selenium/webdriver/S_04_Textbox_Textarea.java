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

public class S_04_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");

		firstName = "Dong";
		lastName = "Do";
		emailAddress = "dongafc" + getRandomNumbers() + "@gmail.com";
		password = "SeJava3@";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();

		driver.findElement(By.xpath("//div[@id='header-account']//a[text()='Register']")).click();

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);

		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);

		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Thank you for registering with Main Website Store.");

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + firstName + " " + lastName + "!");

		String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfo.contains(firstName + " " + lastName));
		Assert.assertTrue(contactInfo.contains(emailAddress));
	}

	@Test
	public void TC_02_Contact_Us() {
		driver.findElement(By.xpath("//a[text()='Contact Us']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='name']")).getAttribute("value"), firstName + " " + lastName);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value"), emailAddress);

		driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys("Automation Testing\nSelenium Java");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumbers() {
		return new Random().nextInt(99999);
	}

}
