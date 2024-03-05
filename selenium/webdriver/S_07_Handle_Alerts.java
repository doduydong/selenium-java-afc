package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_07_Handle_Alerts {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.seleniumeasy.com/javascript-alert-box-demo.html");
	}

	@Test
	public void TC_01_Alert() {
		WebElement alertBtn = driver.findElement(By.xpath("//p[contains(text(),'alert box')]/following-sibling::button"));

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", alertBtn);
		sleepInSecond(1);

		alertBtn.click();
		sleepInSecond(1);

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am an alert box!");
		alert.accept();
	}

	@Test
	public void TC_02_Confirm_Alert() {
		WebElement confirmAlertBtn = driver.findElement(By.xpath("//p[contains(text(),'confirm box')]/following-sibling::button"));
		WebElement confirmAlertMsg = driver.findElement(By.xpath("//p[@id='confirm-demo']"));

		confirmAlertBtn.click();
		sleepInSecond(1);

		Alert confirmAlert = driver.switchTo().alert();
		Assert.assertEquals(confirmAlert.getText(), "Press a button!");
		confirmAlert.dismiss();
		sleepInSecond(1);
		Assert.assertEquals(confirmAlertMsg.getText(), "You pressed Cancel!");

		confirmAlertBtn.click();
		sleepInSecond(1);

		confirmAlert = driver.switchTo().alert();
		Assert.assertEquals(confirmAlert.getText(), "Press a button!");
		confirmAlert.accept();
		sleepInSecond(1);
		Assert.assertEquals(confirmAlertMsg.getText(), "You pressed OK!");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		String keysToSend = "Selenium Java";

		driver.findElement(By.xpath("//p[contains(text(),'prompt box')]/following-sibling::button")).click();
		sleepInSecond(1);

		Alert promptAlert = driver.switchTo().alert();
		Assert.assertEquals(promptAlert.getText(), "Please enter your name");
		promptAlert.sendKeys(keysToSend);
		sleepInSecond(1);
		promptAlert.accept();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='prompt-demo']")).getText(), "You have entered '" + keysToSend + "' !");
	}

	@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com/");

		String basicAuthUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

		String userBasicAuthUrl = getUserBasicAuthUrl(basicAuthUrl, "admin", "admin");

		System.out.println("User Basic Auth Url: " + userBasicAuthUrl);

		driver.get(userBasicAuthUrl);

		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p")).getText(), "Congratulations! You must have the proper credentials.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public String getUserBasicAuthUrl(String basicAuthUrl, String userName, String password) {
		String[] urls = basicAuthUrl.split("//");
		return basicAuthUrl = urls[0] + "//" + userName + ":" + password + "@" + urls[1];
	}

}
