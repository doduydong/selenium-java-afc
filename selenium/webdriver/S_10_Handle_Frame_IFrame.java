package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_10_Handle_Frame_IFrame {
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
	public void TC_01_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));

		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("AFCSJ3");
		sleepInSecond(1);

		driver.findElement(By.xpath("//a[contains(@class,'login-btn')]")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Selenium3@");
		sleepInSecond(1);

		driver.findElement(By.xpath("//a[@id='loginBtn']")).click();
		sleepInSecond(1);

		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(@class,'error-msg')]")).getText(), "Customer ID/IPIN (Password) is invalid. Please try again.");
	}

	@Test
	public void TC_02_IFrame() {
		driver.get("https://skills.kynaenglish.vn/");

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='k-footer']")));
		sleepInSecond(1);

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));

		System.out.println("Followers: " + driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText());

		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='cs-live-chat']/iframe")));

		driver.findElement(By.xpath("//div[@class='meshim_widget_Widget']")).click();
		sleepInSecond(1);

		driver.findElement(By.xpath("//input[contains(@class,'input_name')]")).sendKeys("Dong");

		driver.findElement(By.xpath("//input[contains(@class,'input_phone')]")).sendKeys("0123456789");

		new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");

		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Selenium\nJava\n3");
		sleepInSecond(1);

		driver.switchTo().defaultContent();

		WebElement searchBar = driver.findElement(By.xpath("//input[@id='live-search-bar']"));

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", searchBar);
		sleepInSecond(1);

		searchBar.sendKeys("Selenium Java");
		sleepInSecond(1);
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
