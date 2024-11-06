package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SJ_07_Handle_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
	}

	@Test
	public void TC_01_Alert() {
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		sleepForSeconds(1);

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();
		sleepForSeconds(1);

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You successfully clicked an alert");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		WebElement jsConfirmBtn = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
		WebElement result = driver.findElement(By.xpath("//p[@id='result']"));

		jsConfirmBtn.click();
		sleepForSeconds(1);

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.dismiss();
		sleepForSeconds(1);

		Assert.assertEquals(result.getText(), "You clicked: Cancel");

		jsConfirmBtn.click();
		sleepForSeconds(1);

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.accept();
		sleepForSeconds(1);

		Assert.assertEquals(result.getText(), "You clicked: Ok");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		WebElement jsPromptBtn = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));
		WebElement result = driver.findElement(By.xpath("//p[@id='result']"));
		String textValue = "Selenium Java";

		jsPromptBtn.click();
		sleepForSeconds(1);

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		alert.sendKeys(textValue);

		alert.dismiss();
		sleepForSeconds(1);

		Assert.assertEquals(result.getText(), "You entered: null");

		jsPromptBtn.click();
		sleepForSeconds(1);

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		alert.sendKeys(textValue);

		alert.accept();
		sleepForSeconds(1);

		Assert.assertEquals(result.getText(), "You entered: " + textValue);
	}

	@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com/");

		String basicAuthUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

		driver.get(getUserBasicAuthUrl(basicAuthUrl, "admin", "admin"));

		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']/parent::div/p")).getText(), "Congratulations! You must have the proper credentials.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getUserBasicAuthUrl(String basicAuthUrl, String userName, String password) {
		String[] urls = basicAuthUrl.split("//");
		return basicAuthUrl = urls[0] + "//" + userName + ":" + password + "@" + urls[1];
	}

}
