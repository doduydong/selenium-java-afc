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

public class SJ_04_Handle_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAddress, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");

		firstName = "Dong";
		lastName = "Do";
		fullName = firstName + " " + lastName;
		emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
		password = "SeJava3@";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();

		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Register']")).click();

		driver.findElement(By.xpath("//input[@id='firstname']")).clear();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);

		driver.findElement(By.xpath("//input[@id='lastname']")).clear();
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);

		driver.findElement(By.xpath("//input[@id='email_address']")).clear();
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//input[@id='password']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@id='confirmation']")).clear();
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName + "!");

		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
	}

	@Test
	public void TC_02_Product_Reviews() {
		driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")).click();

		driver.findElement(By.xpath("//div[@class='product-info']//a[text()='IPhone']")).click();

		driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

		driver.findElement(By.xpath("//input[@type='radio' and @value='5']")).click();

		driver.findElement(By.xpath("//textarea[@id='review_field']")).clear();
		driver.findElement(By.xpath("//textarea[@id='review_field']")).sendKeys("Good product\nEasy to use");

		driver.findElement(By.xpath("//input[@id='summary_field']")).clear();
		driver.findElement(By.xpath("//input[@id='summary_field']")).sendKeys("Best phone");

		driver.findElement(By.xpath("//input[@id='nickname_field']")).clear();
		driver.findElement(By.xpath("//input[@id='nickname_field']")).sendKeys(fullName);

		driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Your review has been accepted for moderation.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		return new Random().nextInt(100000);
	}

}
