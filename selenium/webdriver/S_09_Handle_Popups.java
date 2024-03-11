package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_09_Handle_Popups {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fixed_Popup_In_DOM() {
		driver.get("https://ngoaingu24h.vn/");

		driver.findElement(By.xpath("//div[@id='button-login-dialog']/button[@class='login_ icon-before']")).click();
		sleepInSecond(1);

		WebElement popup = driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]/div[@class='modal-dialog']"));

		Assert.assertTrue(popup.isDisplayed());

		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//button[@class='close']")).click();
		sleepInSecond(1);

		Assert.assertFalse(popup.isDisplayed());
	}

	@Test
	public void TC_02_Fixed_Popup_Not_In_DOM() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[text()='Create new account']")).click();
		sleepInSecond(1);

		By popupBy = By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']");

		Assert.assertTrue(driver.findElement(popupBy).isDisplayed());

		driver.findElement(By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']/img")).click();
		sleepInSecond(1);

		Assert.assertEquals(driver.findElements(popupBy).size(), 0);
	}

	@Test
	public void TC_03_Random_Popup_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);

		WebElement popup = driver.findElement(By.xpath("//div[@class='tve_flt']"));

		if (popup.isDisplayed()) {
			driver.findElement(By.xpath("//div[@class='tve_flt']//div[contains(@class,'form_close')]")).click();
			sleepInSecond(1);
		}

		Assert.assertFalse(popup.isDisplayed());
	}

	@Test
	public void TC_04_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);

		By popupBy = By.xpath("//div[@class='popup-content']");
		List<WebElement> popup = driver.findElements(popupBy);

		if (popup.size() > 0 && popup.get(0).isDisplayed()) {
			driver.findElement(By.xpath("//button[@id='close-popup']")).click();
			sleepInSecond(1);
		}

		Assert.assertEquals(driver.findElements(popupBy).size(), 0);
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

}
