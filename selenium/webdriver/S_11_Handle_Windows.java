package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_11_Handle_Windows {
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
	public void TC_01() {
		driver.get("https://skills.kynaenglish.vn/");

		String kynaPageTitle = driver.getTitle();
		String kynaPageID = driver.getWindowHandle();

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='k-footer']")));
		sleepInSecond(1);

		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		sleepInSecond(2);

		switchToWindowDifferentFromCurrentByID(kynaPageID);
		sleepInSecond(1);

		String fbPageTitle = driver.getTitle();
		Assert.assertEquals(fbPageTitle, "Kyna.vn | Ho Chi Minh City | Facebook");

		switchToWindowByExpectedTitle(kynaPageTitle);
		sleepInSecond(1);

		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		sleepInSecond(2);

		switchToWindowDifferentFromCurrentByID(kynaPageID);
		sleepInSecond(1);

		String ytPageTitle = driver.getTitle();
		Assert.assertEquals(ytPageTitle, "Kyna.vn - YouTube");

		closeAllWindowsExceptWindowByID(kynaPageID);
		sleepInSecond(1);

		WebElement searchBar = driver.findElement(By.xpath("//input[@id='live-search-bar']"));

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", searchBar);
		sleepInSecond(1);

		searchBar.sendKeys("Selenium Java");
		sleepInSecond(1);
	}

	@Test
	public void TC_02() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(2);

		String pageTitle = driver.getTitle();
		String pageID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
		sleepInSecond(1);

		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
		sleepInSecond(1);

		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
		sleepInSecond(1);

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(2);

		switchToWindowDifferentFromCurrentByID(pageID);
		driver.manage().window().maximize();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']/parent::div/following-sibling::table//a[text()='IPhone']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']/parent::div/following-sibling::table//a[text()='Samsung Galaxy']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']/parent::div/following-sibling::table//a[text()='Sony Xperia']")).isDisplayed());

		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		sleepInSecond(1);

		switchToWindowByExpectedTitle(pageTitle);

		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(1);

		driver.switchTo().alert().accept();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
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

	public void switchToWindowDifferentFromCurrentByID(String currentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(currentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByExpectedTitle(String expectedTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsExceptWindowByID(String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(windowID);
	}

}
