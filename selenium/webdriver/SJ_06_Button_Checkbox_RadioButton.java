package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SJ_06_Button_Checkbox_RadioButton {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		WebElement loginButton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));

		Assert.assertFalse(loginButton.isEnabled());

		driver.findElement(By.xpath("//li[contains(@class,'popup-login-tab-login')]")).click();
		sleepForSeconds(1);

		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("dongafc@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("SeJava3@");
		sleepForSeconds(1);

		Assert.assertTrue(loginButton.isEnabled());

		String loginBtnColor = Color.fromString(loginButton.getCssValue("background-color")).asHex().toUpperCase();

		Assert.assertEquals(loginBtnColor, "#C92127");
	}

	@Test
	public void TC_02_Default_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");

		WebElement checkedCheckbox = driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input[@type='checkbox']"));
		WebElement disabledCheckbox = driver.findElement(By.xpath("//label[text()='Disabled']/preceding-sibling::div/input[@type='checkbox']"));
		WebElement resultCheckbox = driver.findElement(By.xpath("//label[contains(text(),concat('I',\"'m a checkbox\"))]/preceding-sibling::div/input[@type='checkbox']"));

		Assert.assertTrue(resultCheckbox.isEnabled());
		Assert.assertFalse(resultCheckbox.isSelected());

		checkCheckboxOrRadioButton(checkedCheckbox);

		Assert.assertTrue(resultCheckbox.isEnabled());
		Assert.assertTrue(resultCheckbox.isSelected());

		checkCheckboxOrRadioButton(disabledCheckbox);

		Assert.assertFalse(resultCheckbox.isEnabled());
		Assert.assertTrue(resultCheckbox.isSelected());

		uncheckCheckbox(checkedCheckbox);

		Assert.assertFalse(resultCheckbox.isEnabled());
		Assert.assertFalse(resultCheckbox.isSelected());

		uncheckCheckbox(disabledCheckbox);

		Assert.assertTrue(resultCheckbox.isEnabled());
		Assert.assertFalse(resultCheckbox.isSelected());
	}

	@Test
	public void TC_03_Default_RadioButton() {
		driver.get("https://material.angular.io/components/radio/examples");

		String value = "Spring";

		WebElement winterRadioBtn = driver.findElement(By.xpath("//label[text()='" + value + "']/preceding-sibling::div/input"));

		Assert.assertFalse(winterRadioBtn.isSelected());

		checkCheckboxOrRadioButton(winterRadioBtn);

		Assert.assertTrue(winterRadioBtn.isSelected());

		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your favorite season is: " + value + "']")).isDisplayed());
	}

	@Test
	public void TC_04_Custom_Checkbox_RadioButton() {
		// RadioButton
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform?pli=1");

		WebElement hanoiRadioBtn = driver.findElement(By.xpath("//div[@aria-label='Hà Nội' and @role='radio']"));

		Assert.assertEquals(hanoiRadioBtn.getAttribute("aria-checked"), "false");

		jsExecutor.executeScript("arguments[0].click();", hanoiRadioBtn);
		sleepForSeconds(1);

		Assert.assertEquals(hanoiRadioBtn.getAttribute("aria-checked"), "true");

		// Checkbox
		List<WebElement> quangCheckboxes = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng') and @role='checkbox']"));

		for (WebElement checkbox : quangCheckboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "false");
		}

		for (WebElement checkbox : quangCheckboxes) {
			jsExecutor.executeScript("arguments[0].click();", checkbox);
			sleepForSeconds(1);
		}

		for (WebElement checkbox : quangCheckboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}
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

	public void checkCheckboxOrRadioButton(WebElement element) {
		if (element.isEnabled() && !element.isSelected()) {
			element.click();
		}
	}

	public void uncheckCheckbox(WebElement element) {
		if (element.isEnabled() && element.isSelected()) {
			element.click();
		}
	}

}
