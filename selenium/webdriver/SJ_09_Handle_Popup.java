package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SJ_09_Handle_Popup {
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
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");

		driver.findElement(By.xpath("//div[@class='action']/button[text()='Đăng nhập']")).click();
		sleepForSeconds(1);

		By popupBy = By.xpath("//div[@role='dialog']");

		Assert.assertTrue(driver.findElement(popupBy).isDisplayed());

		driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("dongsdet@gmail.com");

		driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("dongsdet@gmail.com");

		driver.findElement(By.xpath("//button[contains(@class,'dialog-button') and @type='submit']")).click();
		sleepForSeconds(1);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='notistack-snackbar' and text()='Bạn đã nhập sai tài khoản hoặc mật khẩu!']")).isDisplayed());

		driver.findElement(By.xpath("//button[contains(@class,'close-btn')]")).click();
		sleepForSeconds(1);

		Assert.assertEquals(driver.findElements(popupBy).size(), 0);
	}

	@Test
	public void TC_02_Random_Popup() {
		driver.get("https://dehieu.vn/");
		sleepForSeconds(5);

		WebElement popup = driver.findElement(By.xpath("//div[contains(@class,'modal-dialog')]"));

		if (popup.isDisplayed()) {
			driver.findElement(By.xpath("//h5[text()='Form Đăng Ký']/following-sibling::button[@class='close']")).click();
			sleepForSeconds(1);
		}

		Assert.assertFalse(popup.isDisplayed());
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

}
