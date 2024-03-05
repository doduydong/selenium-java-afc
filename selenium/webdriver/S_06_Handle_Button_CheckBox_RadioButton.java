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

public class S_06_Handle_Button_CheckBox_RadioButton {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.xpath("//li[contains(@class,'popup-login-tab-login')]")).click();
		sleepInSecond(1);

		WebElement loginBtn = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));

		Assert.assertFalse(loginBtn.isEnabled());

		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0123456789");

		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("Selenium3@");

		sleepInSecond(1);

		Assert.assertTrue(loginBtn.isEnabled());

		String loginBtnColor = Color.fromString(loginBtn.getCssValue("background-color")).asHex().toUpperCase();

		Assert.assertEquals(loginBtnColor, "#C92127");
	}

	@Test
	public void TC_02_Default_Checkbox_RadioButton() {
		// Checkbox
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		checkToDefaultCheckboxOrRadioButton("//label[text()='Heated front and rear seats']/preceding-sibling::input[@type='checkbox']");

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input[@type='checkbox']")).isSelected());

		uncheckToDefaultCheckbox("//label[text()='Rear side airbags']/preceding-sibling::input[@type='checkbox']");

		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input[@type='checkbox']")).isSelected());

		// RadioButton
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input[@type='radio']")).isSelected());

		checkToDefaultCheckboxOrRadioButton("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input[@type='radio']");

		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input[@type='radio']")).isSelected());

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input[@type='radio']")).isSelected());
	}

	@Test
	public void TC_03_Custom_Checkbox_RadioButton() {
		// RadioButton
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		WebElement hanoiRadioBtn = driver.findElement(By.xpath("//div[@aria-label='Hà Nội' and @role='radio']"));

		Assert.assertEquals(hanoiRadioBtn.getAttribute("aria-checked"), "false");

		jsExecutor.executeScript("arguments[0].click();", hanoiRadioBtn);
		sleepInSecond(1);

		Assert.assertEquals(hanoiRadioBtn.getAttribute("aria-checked"), "true");

		// CheckBox
		List<WebElement> quangCheckboxes = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng') and @role='checkbox']"));

		for (WebElement checkbox : quangCheckboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "false");
		}

		for (WebElement checkbox : quangCheckboxes) {
			if (!checkbox.isSelected() && checkbox.isEnabled()) {
				jsExecutor.executeScript("arguments[0].click();", checkbox);
				sleepInSecond(1);
			}
		}

		for (WebElement checkbox : quangCheckboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}
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

	public void checkToDefaultCheckboxOrRadioButton(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected() && element.isEnabled()) {
			element.click();
			sleepInSecond(1);
		}
	}

	public void uncheckToDefaultCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected() && element.isEnabled()) {
			element.click();
			sleepInSecond(1);
		}
	}

}
