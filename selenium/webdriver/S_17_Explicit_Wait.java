package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_17_Explicit_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Start']"))).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

		explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='finish']/h4"), "Hello World!"));
	}

	@Test
	public void TC_02() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='datesContainer']//span"), "No Selected Dates to display."));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='calendarContainer']//a[text()='23']"))).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'RadCalendar1')]//div[@class='raDiv']")));

		explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='datesContainer']//span"), "Saturday, March 23, 2024"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
