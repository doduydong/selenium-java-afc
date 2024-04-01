package testcases;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T_07_InvocationCount {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAddress, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(description = "Create User Accounts", invocationCount = 3)
	public void Register() {
		firstName = "Dong";
		lastName = "Do";
		fullName = firstName + " " + lastName;
		emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
		password = "Selenium3@";

		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);

		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);

		driver.findElement(By.xpath("//button[@id='register-button']")).click();

		driver.findElement(By.xpath("//a[contains(@class,'register-continue-button')]")).click();

		System.out.println("Email & Password: " + emailAddress + "/ " + password);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}

}
